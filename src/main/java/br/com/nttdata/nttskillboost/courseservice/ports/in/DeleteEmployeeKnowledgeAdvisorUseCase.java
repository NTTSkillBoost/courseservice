package br.com.nttdata.nttskillboost.courseservice.ports.in;

import java.util.UUID;

public interface DeleteEmployeeKnowledgeAdvisorUseCase {
    boolean delete(UUID id);
}
