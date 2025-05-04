package br.com.nttdata.nttskillboost.courseservice.adapters.api.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

@Data
@Builder
public class EmployeeKnowledgeAdvisorRequest {

    @Schema(description = "Nome do funcionário", example = "João da Silva")
    private String name;

    @Schema(description = "Bio do funcionário", example = "123.456.789-00")
    private String bio;

    @Schema(description = "ID do Edereço no sistema de terceiros", example = "123e4567-e89b-12d3-a456-426614174000")
    private UUID employeeId;

    private LocalDate startDate;
}
