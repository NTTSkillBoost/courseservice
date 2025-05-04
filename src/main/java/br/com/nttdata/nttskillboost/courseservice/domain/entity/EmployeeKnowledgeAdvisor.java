package br.com.nttdata.nttskillboost.courseservice.domain.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "tb_employee_knowledge_advisor")
@Inheritance(strategy = InheritanceType.JOINED)
@Data
@Builder
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Tag(name = "EmployeeKnowledgeAdvisor Entity", description = "Operações relacionadas a Função do funcionário")
public class EmployeeKnowledgeAdvisor extends AuditDomain {

    @Schema(description = "ID do EmployeeRole", example = "123e4567-e89b-12d3-a456-426614174000")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Schema(description = "Nome do funcionário", example = "João da Silva")
    private String name;

    @Schema(description = "Bio do funcionário", example = "123.456.789-00")
    private String bio;

    @Schema(description = "ID do Edereço no sistema de terceiros", example = "123e4567-e89b-12d3-a456-426614174000")
    @Column(name = "employee_id")
    private UUID employeeId;

    @Schema(description = "ID do Edereço no sistema de terceiros", example = "123e4567-e89b-12d3-a456-426614174000")
    @Column(name = "start_date")
    private LocalDate startDate;
}
