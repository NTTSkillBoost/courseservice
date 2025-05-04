package br.com.nttdata.nttskillboost.courseservice.adapters.repository;

import br.com.nttdata.nttskillboost.courseservice.domain.entity.EmployeeKnowledgeAdvisor;
import br.com.nttdata.nttskillboost.courseservice.ports.out.EmployeeKnowledgeAdvisorRepositoryPort;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class EmployeeKnowledgeAdvisorRepositoryAdapter implements EmployeeKnowledgeAdvisorRepositoryPort {

    private final EmployeeKnowledgeAdvisorJpaRepository employeeKnowledgeAdvisorJpaRepository;

    @Override
    public Optional<EmployeeKnowledgeAdvisor> findByEmployeeId(UUID employeeId) {
        return employeeKnowledgeAdvisorJpaRepository.findByEmployeeId(employeeId);
    }

    @Override
    public EmployeeKnowledgeAdvisor save(EmployeeKnowledgeAdvisor employeeKnowledgeAdvisor) {
        return employeeKnowledgeAdvisorJpaRepository.save(employeeKnowledgeAdvisor);
    }

    @Override
    public EmployeeKnowledgeAdvisor findById(UUID id) {
        Optional<EmployeeKnowledgeAdvisor> byId = employeeKnowledgeAdvisorJpaRepository.findById(id);
        if (byId.isPresent()) {
            return byId.get();
        } else {
            throw new EntityNotFoundException("Funcionário com ID " + id + " não encontrado");
        }
    }

    @Override
    public List<EmployeeKnowledgeAdvisor> findAll() {
        return employeeKnowledgeAdvisorJpaRepository.findAll();
    }
}
