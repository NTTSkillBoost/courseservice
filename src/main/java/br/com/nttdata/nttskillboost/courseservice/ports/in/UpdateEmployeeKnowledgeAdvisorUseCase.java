package br.com.nttdata.nttskillboost.courseservice.ports.in;

import br.com.nttdata.nttskillboost.courseservice.domain.entity.EmployeeKnowledgeAdvisor;

import java.util.UUID;

public interface UpdateEmployeeKnowledgeAdvisorUseCase {
    EmployeeKnowledgeAdvisor update(UUID id, EmployeeKnowledgeAdvisor employee);
}
