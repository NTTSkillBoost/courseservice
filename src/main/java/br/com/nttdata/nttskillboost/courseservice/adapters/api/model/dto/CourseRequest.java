package br.com.nttdata.nttskillboost.courseservice.adapters.api.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CourseRequest {

    @Schema(description = "Nome do funcionário", example = "João da Silva")
    private String name;

    @Schema(description = "Descrição do funcionário", example = "123.456.789-00")
    private String description;

    @Schema(description = "Ementa do curso", example = "123e4567-e89b-12d3-a456-426614174000")
    private String syllabus;

    @Schema(description = "meta_pontos", example = "40")
    private Integer goalPoints;

    @Schema(description = "ID do Edereço no sistema de terceiros", example = "123e4567-e89b-12d3-a456-426614174000")
    private LocalDate makeDate;

    @Schema(description = "ID do Edereço no sistema de terceiros", example = "123e4567-e89b-12d3-a456-426614174000")
    private String courseStatus;

    @Schema(description = "ID do Edereço no sistema de terceiros", example = "123e4567-e89b-12d3-a456-426614174000")
    private String positionLevel;

    @Schema(description = "Employee", example = "Employee")
    private UUID employeeKnowledgeAdvisorId;
}
