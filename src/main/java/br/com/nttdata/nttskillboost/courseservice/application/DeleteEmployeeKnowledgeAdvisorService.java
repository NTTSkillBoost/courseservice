package br.com.nttdata.nttskillboost.courseservice.application;

import br.com.nttdata.nttskillboost.courseservice.domain.entity.EmployeeKnowledgeAdvisor;
import br.com.nttdata.nttskillboost.courseservice.domain.entity.Status;
import br.com.nttdata.nttskillboost.courseservice.ports.in.DeleteEmployeeKnowledgeAdvisorUseCase;
import br.com.nttdata.nttskillboost.courseservice.ports.out.EmployeeKnowledgeAdvisorRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DeleteEmployeeKnowledgeAdvisorService implements DeleteEmployeeKnowledgeAdvisorUseCase {

    private final EmployeeKnowledgeAdvisorRepositoryPort employeeKnowledgeAdvisorRepositoryPort;

    @Override
    public boolean delete(UUID id) {
        EmployeeKnowledgeAdvisor byId = employeeKnowledgeAdvisorRepositoryPort.findById(id);
        if (byId != null) {
            byId.setStatus(Status.DELETED);
            employeeKnowledgeAdvisorRepositoryPort.save(byId);
            return true;
        } else {
            return false;
        }
    }
}
