package br.com.nttdata.nttskillboost.courseservice.ports.in;

import br.com.nttdata.nttskillboost.courseservice.domain.entity.EmployeeKnowledgeAdvisor;

public interface CreateEmployeeKnowledgeAdvisorUseCase {
    EmployeeKnowledgeAdvisor create(EmployeeKnowledgeAdvisor employee);
}
