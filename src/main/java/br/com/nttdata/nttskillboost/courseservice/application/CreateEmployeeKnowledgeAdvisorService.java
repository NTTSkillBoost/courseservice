package br.com.nttdata.nttskillboost.courseservice.application;

import br.com.nttdata.nttskillboost.courseservice.adapters.exception.BusinessException;
import br.com.nttdata.nttskillboost.courseservice.adapters.gateway.EmployeeClient;
import br.com.nttdata.nttskillboost.courseservice.application.exception.ResourceAlwaysExists;
import br.com.nttdata.nttskillboost.courseservice.domain.entity.EmployeeKnowledgeAdvisor;
import br.com.nttdata.nttskillboost.courseservice.ports.in.CreateEmployeeKnowledgeAdvisorUseCase;
import br.com.nttdata.nttskillboost.courseservice.ports.out.EmployeeKnowledgeAdvisorRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateEmployeeKnowledgeAdvisorService implements CreateEmployeeKnowledgeAdvisorUseCase {

    private final EmployeeKnowledgeAdvisorRepositoryPort employeeKnowledgeAdvisorRepositoryPort;
    private final EmployeeClient employeeClient;

    @Override
    public EmployeeKnowledgeAdvisor create(EmployeeKnowledgeAdvisor employeeKnowledgeAdvisor) {
        if (employeeKnowledgeAdvisorRepositoryPort.findByEmployeeId(employeeKnowledgeAdvisor.getEmployeeId()).isPresent()) {
            throw new ResourceAlwaysExists("Já existe funcionário com este email.");
        }

        boolean employeeExists = employeeClient.existsById(employeeKnowledgeAdvisor.getEmployeeId());
        if (!employeeExists) {
            throw new BusinessException("Employee ID inválido.");
        }
        return employeeKnowledgeAdvisorRepositoryPort.save(employeeKnowledgeAdvisor);
    }
}
