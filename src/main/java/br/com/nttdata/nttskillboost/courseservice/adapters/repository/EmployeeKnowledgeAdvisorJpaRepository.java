package br.com.nttdata.nttskillboost.courseservice.adapters.repository;

import br.com.nttdata.nttskillboost.courseservice.domain.entity.EmployeeKnowledgeAdvisor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface EmployeeKnowledgeAdvisorJpaRepository extends JpaRepository<EmployeeKnowledgeAdvisor, UUID> {
    Optional<EmployeeKnowledgeAdvisor> findByEmployeeId(UUID employeeId);
}
