package br.com.nttdata.nttskillboost.courseservice.application;

import br.com.nttdata.nttskillboost.courseservice.domain.entity.EmployeeKnowledgeAdvisor;
import br.com.nttdata.nttskillboost.courseservice.ports.in.GetEmployeeKnowledgeAdvisorUseCase;
import br.com.nttdata.nttskillboost.courseservice.ports.out.EmployeeKnowledgeAdvisorRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class GetEmployeeKnowledgeAdvisorService implements GetEmployeeKnowledgeAdvisorUseCase {

    private final EmployeeKnowledgeAdvisorRepositoryPort employeeKnowledgeAdvisorRepositoryPort;

    @Override
    public EmployeeKnowledgeAdvisor findById(UUID id) {
        return employeeKnowledgeAdvisorRepositoryPort.findById(id);
    }

    @Override
    public List<EmployeeKnowledgeAdvisor> findAll() {
        return employeeKnowledgeAdvisorRepositoryPort.findAll();
    }
}
