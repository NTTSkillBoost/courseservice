package br.com.nttdata.nttskillboost.courseservice.ports.out;

import br.com.nttdata.nttskillboost.courseservice.domain.entity.EmployeeKnowledgeAdvisor;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface EmployeeKnowledgeAdvisorRepositoryPort {
    Optional<EmployeeKnowledgeAdvisor> findByEmployeeId(UUID employeeId);
    EmployeeKnowledgeAdvisor save(EmployeeKnowledgeAdvisor employeeKnowledgeAdvisor);
    EmployeeKnowledgeAdvisor findById(UUID id);
    List<EmployeeKnowledgeAdvisor> findAll();
}
