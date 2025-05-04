package br.com.nttdata.nttskillboost.courseservice.adapters.api.controller;

import br.com.nttdata.nttskillboost.courseservice.adapters.api.model.dto.EmployeeKnowledgeAdvisorRequest;
import br.com.nttdata.nttskillboost.courseservice.adapters.api.model.dto.EmployeeKnowledgeAdvisorResponse;
import br.com.nttdata.nttskillboost.courseservice.adapters.api.model.mapper.EmployeeKnowledgeAdvisorMapper;
import br.com.nttdata.nttskillboost.courseservice.application.CreateEmployeeKnowledgeAdvisorService;
import br.com.nttdata.nttskillboost.courseservice.application.DeleteEmployeeKnowledgeAdvisorService;
import br.com.nttdata.nttskillboost.courseservice.application.GetEmployeeKnowledgeAdvisorService;
import br.com.nttdata.nttskillboost.courseservice.application.UpdateEmployeeKnowledgeAdvisorService;
import br.com.nttdata.nttskillboost.courseservice.domain.entity.EmployeeKnowledgeAdvisor;
import br.com.nttdata.nttskillboost.courseservice.domain.entity.Status;
import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/v1/employee-knowledge-advisors")
@RequiredArgsConstructor
public class EmployeeKnowledgeAdvisorController {

    private final CreateEmployeeKnowledgeAdvisorService createEmployeeKnowledgeAdvisorService;
    private final GetEmployeeKnowledgeAdvisorService getEmployeeKnowledgeAdvisorService;
    private final UpdateEmployeeKnowledgeAdvisorService updateEmployeeKnowledgeAdvisorService;
    private final DeleteEmployeeKnowledgeAdvisorService deleteEmployeeKnowledgeAdvisorService;
    private final EmployeeKnowledgeAdvisorMapper employeeKnowledgeAdvisorMapper;

    //@Retry(name = "employeeService", fallbackMethod = "fallbackCreate")
    //@CircuitBreaker(name = "employeeService", fallbackMethod = "fallbackCreate")
    //@Bulkhead(name = "employeeService")
    @PostMapping
    public ResponseEntity<EmployeeKnowledgeAdvisorResponse> create(@RequestBody EmployeeKnowledgeAdvisorRequest dto) {
        // 🔥 Simular falha controlada
        if ("FAIL".equalsIgnoreCase(dto.getName())) {
            throw new RuntimeException("Falha simulada para teste de Resilience4j.");
        }

        EmployeeKnowledgeAdvisor employee = employeeKnowledgeAdvisorMapper.toDomain(dto);
        EmployeeKnowledgeAdvisor created = createEmployeeKnowledgeAdvisorService.create(employee);
        if (created == null) {
            return ResponseEntity.badRequest().build();
        }

        URI location = URI.create(String.format("/v1/employees/%s", created.getId()));
        EmployeeKnowledgeAdvisorResponse response = employeeKnowledgeAdvisorMapper.toResponse(created);
        return ResponseEntity.created(location).body(response);
    }

    // 🔎 Buscar por ID
    @GetMapping("/{id}")
    public ResponseEntity<EmployeeKnowledgeAdvisorResponse> findById(@PathVariable UUID id) {
        EmployeeKnowledgeAdvisor employeeById = getEmployeeKnowledgeAdvisorService.findById(id);
        if (employeeById != null) {
            EmployeeKnowledgeAdvisorResponse byId = employeeKnowledgeAdvisorMapper.toResponse(employeeById);
            return ResponseEntity.ok(byId);
        }

        return ResponseEntity.notFound().build();
    }

    // 🔎 Listar todos
    @GetMapping
    public List<EmployeeKnowledgeAdvisorResponse> listAll() {
        List<EmployeeKnowledgeAdvisor> employeeKnowledgeAdvisors = getEmployeeKnowledgeAdvisorService.findAll();
        return employeeKnowledgeAdvisors.stream()
                .map(employeeKnowledgeAdvisorMapper::toResponse)
                .toList();
    }

    // 🔄 Atualizar
    @PutMapping("/{id}")
    public ResponseEntity<EmployeeKnowledgeAdvisorResponse> update(@PathVariable UUID id, @RequestBody EmployeeKnowledgeAdvisorRequest dto) {
        EmployeeKnowledgeAdvisor employeeKnowledgeAdvisor = employeeKnowledgeAdvisorMapper.toDomain(dto);
        EmployeeKnowledgeAdvisor update = updateEmployeeKnowledgeAdvisorService.update(id, employeeKnowledgeAdvisor);
        if (update != null) {
            EmployeeKnowledgeAdvisorResponse response = employeeKnowledgeAdvisorMapper.toResponse(update);
            return ResponseEntity.ok(response);
        }
        return ResponseEntity.notFound().build();
    }

    // ❌ Deletar
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        if (deleteEmployeeKnowledgeAdvisorService.delete(id)) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    public ResponseEntity<EmployeeKnowledgeAdvisorResponse> fallbackCreate(EmployeeKnowledgeAdvisorRequest dto, Throwable t) {
        log.error("Fallback method called due to: {}", t.getMessage());

        EmployeeKnowledgeAdvisor fallback = new EmployeeKnowledgeAdvisor();
        fallback.setBio(dto.getBio());
        fallback.setName(dto.getName());
        fallback.setStartDate(dto.getStartDate());
        fallback.setEmployeeId(dto.getEmployeeId());
        fallback.setStatus(Status.INACTIVE);
        EmployeeKnowledgeAdvisorResponse response = employeeKnowledgeAdvisorMapper.toResponse(fallback);
        return ResponseEntity.ok(response);
    }
}
