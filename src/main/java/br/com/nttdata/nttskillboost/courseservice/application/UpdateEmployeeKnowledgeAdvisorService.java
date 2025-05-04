package br.com.nttdata.nttskillboost.courseservice.application;

import br.com.nttdata.nttskillboost.courseservice.domain.entity.EmployeeKnowledgeAdvisor;
import br.com.nttdata.nttskillboost.courseservice.ports.in.UpdateEmployeeKnowledgeAdvisorUseCase;
import br.com.nttdata.nttskillboost.courseservice.ports.out.EmployeeKnowledgeAdvisorRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UpdateEmployeeKnowledgeAdvisorService implements UpdateEmployeeKnowledgeAdvisorUseCase {

    private final EmployeeKnowledgeAdvisorRepositoryPort employeeKnowledgeAdvisorRepositoryPort;

    @Override
    public EmployeeKnowledgeAdvisor update(UUID id, EmployeeKnowledgeAdvisor employeeKnowledgeAdvisor) {
        EmployeeKnowledgeAdvisor byId = employeeKnowledgeAdvisorRepositoryPort.findById(id);
        if (byId == null) {
            return null;
        }

        EmployeeKnowledgeAdvisor employeeKnowledgeAdvisorUpdate = new EmployeeKnowledgeAdvisor();
        employeeKnowledgeAdvisorUpdate.setId(byId.getId());
        employeeKnowledgeAdvisorUpdate.setName(employeeKnowledgeAdvisor.getName());
        employeeKnowledgeAdvisorUpdate.setBio(employeeKnowledgeAdvisor.getBio());
        employeeKnowledgeAdvisorUpdate.setEmployeeId(employeeKnowledgeAdvisor.getEmployeeId());
        employeeKnowledgeAdvisorUpdate.setStartDate(employeeKnowledgeAdvisor.getStartDate());
        employeeKnowledgeAdvisorUpdate.setStatus(employeeKnowledgeAdvisor.getStatus());

        return employeeKnowledgeAdvisorRepositoryPort.save(employeeKnowledgeAdvisorUpdate);
    }
}
