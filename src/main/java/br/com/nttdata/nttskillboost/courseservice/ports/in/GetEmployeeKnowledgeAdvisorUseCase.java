package br.com.nttdata.nttskillboost.courseservice.ports.in;

import br.com.nttdata.nttskillboost.courseservice.domain.entity.EmployeeKnowledgeAdvisor;

import java.util.List;
import java.util.UUID;

public interface GetEmployeeKnowledgeAdvisorUseCase {
    EmployeeKnowledgeAdvisor findById(UUID id);
    List<EmployeeKnowledgeAdvisor> findAll();
}
