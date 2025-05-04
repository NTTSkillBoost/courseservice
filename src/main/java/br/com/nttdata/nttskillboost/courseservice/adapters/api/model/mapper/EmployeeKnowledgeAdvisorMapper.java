package br.com.nttdata.nttskillboost.courseservice.adapters.api.model.mapper;


import br.com.nttdata.nttskillboost.courseservice.adapters.api.model.dto.EmployeeKnowledgeAdvisorRequest;
import br.com.nttdata.nttskillboost.courseservice.adapters.api.model.dto.EmployeeKnowledgeAdvisorResponse;
import br.com.nttdata.nttskillboost.courseservice.domain.entity.EmployeeKnowledgeAdvisor;
import org.springframework.stereotype.Component;

@Component
public class EmployeeKnowledgeAdvisorMapper {

    public EmployeeKnowledgeAdvisor toDomain(EmployeeKnowledgeAdvisorRequest dto) {
        return EmployeeKnowledgeAdvisor.builder()
                .name(dto.getName())
                .bio(dto.getBio())
                .employeeId(dto.getEmployeeId())
                .startDate(dto.getStartDate())
                .build();
    }

    public EmployeeKnowledgeAdvisorResponse toResponse(EmployeeKnowledgeAdvisor employeeKnowledgeAdvisor) {
        return EmployeeKnowledgeAdvisorResponse.builder()
                .id(employeeKnowledgeAdvisor.getId())
                .name(employeeKnowledgeAdvisor.getName())
                .bio(employeeKnowledgeAdvisor.getBio())
                .employeeId(employeeKnowledgeAdvisor.getEmployeeId())
                .startDate(employeeKnowledgeAdvisor.getStartDate())
                .build();
    }
}
