package br.com.nttdata.nttskillboost.courseservice;

import br.com.nttdata.nttskillboost.courseservice.adapters.api.model.dto.CourseRequest;
import br.com.nttdata.nttskillboost.courseservice.adapters.api.model.dto.CourseResponse;
import br.com.nttdata.nttskillboost.courseservice.domain.entity.Course;
import br.com.nttdata.nttskillboost.courseservice.domain.entity.CourseStatus;
import br.com.nttdata.nttskillboost.courseservice.domain.entity.EmployeeKnowledgeAdvisor;
import br.com.nttdata.nttskillboost.courseservice.domain.entity.PositionLevel;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.UUID;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CourseControllerResilienceTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void testRetryAndFallback() {
        CourseRequest request = CourseRequest.builder()
                .name("FAIL")
                .description("Test")
                .syllabus("Test")
                .goalPoints(10)
                .makeDate(LocalDate.now())
                .courseStatus(CourseStatus.WIP.getDescription())
                .positionLevel(PositionLevel.JUNIOR.getDescription())
                .employeeKnowledgeAdvisorId(UUID.randomUUID())
                .build();

        ResponseEntity<CourseResponse> response = restTemplate.postForEntity(
                "http://localhost:8881/api/v1/courses", request, CourseResponse.class);

        Assertions.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());

        // Mockito.verify não funciona em chamada REST com o contexto real
    }

    @Test
    void testBulkheadWithConcurrentRequests() throws InterruptedException {
        int concurrentRequests = 15; // bulkhead está configurado para aceitar até 10
        ExecutorService executor = Executors.newFixedThreadPool(concurrentRequests);

        CountDownLatch latch = new CountDownLatch(concurrentRequests);

        for (int i = 0; i < concurrentRequests; i++) {
            int requestNumber = i + 1;
            executor.submit(() -> {
                try {
                    Course request = Course.builder()
                            .name("Concurrent " + requestNumber)
                            .description("Concurrent " + requestNumber)
                            .syllabus("Concurrent " + requestNumber)
                            .goalPoints(requestNumber)
                            .makeDate(LocalDate.now())
                            .courseStatus(CourseStatus.WIP)
                            .positionLevel(PositionLevel.JUNIOR)
                            .employeeKnowledgeAdvisor(EmployeeKnowledgeAdvisor.builder().id(UUID.randomUUID()).build())
                            .build();

                    ResponseEntity<String> response = restTemplate.postForEntity(
                            "http://localhost:8881/api/v1/courses", request, String.class);

                    if (response.getStatusCode() == HttpStatus.SERVICE_UNAVAILABLE) {
                        System.out.println("Erro retornado pela API: " + response.getBody());
                    } else if (response.getStatusCode().is2xxSuccessful()) {
                        try {
                            CourseResponse courseResponse = new ObjectMapper().readValue(response.getBody(), CourseResponse.class);
                            System.out.println("Resposta bem-sucedida: " + courseResponse);
                        } catch (JsonProcessingException e) {
                            System.out.println("Erro ao processar JSON: " + e.getMessage());
                        }
                    } else {
                        System.out.println("Resposta inesperada: " + response.getBody());
                    }

                    System.out.println("Request " + requestNumber + " status: " + response.getStatusCode());
                } catch (Exception e) {
                    System.out.println("Request " + requestNumber + " failed: " + e.getMessage());
                } finally {
                    latch.countDown();
                }
            });
        }

        latch.await(); // Aguarda todas as requisições terminarem
        executor.shutdown();
    }
}