package br.com.nttdata.nttskillboost.courseservice.adapters.api.controller;

import br.com.nttdata.nttskillboost.courseservice.adapters.api.model.dto.CourseRequest;
import br.com.nttdata.nttskillboost.courseservice.adapters.api.model.dto.CourseResponse;
import br.com.nttdata.nttskillboost.courseservice.adapters.api.model.mapper.CourseMapper;
import br.com.nttdata.nttskillboost.courseservice.application.CreateCourseService;
import br.com.nttdata.nttskillboost.courseservice.application.DeleteCourseService;
import br.com.nttdata.nttskillboost.courseservice.application.GetCourseService;
import br.com.nttdata.nttskillboost.courseservice.application.UpdateCourseService;
import br.com.nttdata.nttskillboost.courseservice.domain.entity.Course;
import br.com.nttdata.nttskillboost.courseservice.domain.entity.Status;
import io.github.resilience4j.bulkhead.BulkheadFullException;
import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.circuitbreaker.CallNotPermittedException;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/v1/courses")
@RequiredArgsConstructor
public class CourseController {

    private final CreateCourseService createCourseService;
    private final GetCourseService getCourseService;
    private final UpdateCourseService updateCourseService;
    private final DeleteCourseService deleteCourseService;
    private final CourseMapper courseMapper;

    @Retry(name = "courseService", fallbackMethod = "fallbackCreate")
    @CircuitBreaker(name = "courseService", fallbackMethod = "fallbackCreate")
    @Bulkhead(name = "courseService")
    @PostMapping
    public ResponseEntity<CourseResponse> create(@RequestBody CourseRequest dto) {
        // 🔥 Simular falha controlada
        if ("FAIL".equalsIgnoreCase(dto.getName())) {
            throw new RuntimeException("Falha simulada para teste de Resilience4j.");
        }

        Course course = courseMapper.toDomain(dto);
        Course created = createCourseService.create(course);
        if (created == null) {
            return ResponseEntity.badRequest().build();
        }

        URI location = URI.create(String.format("/v1/courses/%s", created.getId()));
        CourseResponse response = courseMapper.toResponse(created);
        return ResponseEntity.created(location).body(response);
    }

    // 🔎 Buscar por ID
    @GetMapping("/{id}")
    public ResponseEntity<CourseResponse> findById(@PathVariable UUID id) {
        Course courseById = getCourseService.findById(id);
        if (courseById != null) {
            CourseResponse byId = courseMapper.toResponse(courseById);
            return ResponseEntity.ok(byId);
        }

        return ResponseEntity.notFound().build();
    }

    // 🔎 Listar todos
    @GetMapping
    public List<CourseResponse> listAll() {
        List<Course> courses = getCourseService.findAll();
        return courses.stream()
                .map(courseMapper::toResponse)
                .toList();
    }

    // 🔄 Atualizar
    @PutMapping("/{id}")
    public ResponseEntity<CourseResponse> update(@PathVariable UUID id, @RequestBody CourseRequest dto) {
        Course course = courseMapper.toDomain(dto);
        Course update = updateCourseService.update(id, course);
        if (update != null) {
            CourseResponse response = courseMapper.toResponse(update);
            return ResponseEntity.ok(response);
        }
        return ResponseEntity.notFound().build();
    }

    // ❌ Deletar
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        if (deleteCourseService.delete(id)) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    public ResponseEntity<CourseResponse> fallbackCreate(CourseRequest dto, Throwable t) {
        Course fallback = new Course();
        fallback.setName("Fallback " + dto.getName());
        fallback.setDescription("Fallback " + dto.getDescription());
        fallback.setSyllabus("Fallback " + dto.getSyllabus());
        fallback.setGoalPoints(dto.getGoalPoints());
        fallback.setMakeDate(dto.getMakeDate());
        fallback.setStatus(Status.INACTIVE);

        CourseResponse response = courseMapper.toResponse(fallback);

        if (t instanceof BulkheadFullException || t instanceof CallNotPermittedException) {
            return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(response);
        }

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }
}
