package br.com.nttdata.nttskillboost.courseservice.domain.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "tb_course")
@Inheritance(strategy = InheritanceType.JOINED)
@Data
@Builder
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Tag(name = "Course Entity", description = "Operações relacionadas a o funcionário")
public class Course extends AuditDomain {

    @Schema(description = "ID do Employee", example = "123e4567-e89b-12d3-a456-426614174000")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Schema(description = "Nome do funcionário", example = "João da Silva")
    private String name;

    @Schema(description = "Descrição do funcionário", example = "123.456.789-00")
    private String description;

    @Schema(description = "Ementa do curso", example = "123e4567-e89b-12d3-a456-426614174000")
    private String syllabus;

    @Schema(description = "meta_pontos", example = "40")
    @Column(name = "goal_points")
    private Integer goalPoints;

    @Schema(description = "ID do Edereço no sistema de terceiros", example = "123e4567-e89b-12d3-a456-426614174000")
    @Column(name = "make_date")
    private LocalDate makeDate;

    @Schema(description = "ID do Edereço no sistema de terceiros", example = "123e4567-e89b-12d3-a456-426614174000")
    @Column(name = "course_status")
    @Enumerated(EnumType.STRING)
    private CourseStatus courseStatus;

    @Schema(description = "ID do Edereço no sistema de terceiros", example = "123e4567-e89b-12d3-a456-426614174000")
    @Column(name = "position_level")
    @Enumerated(EnumType.STRING)
    private PositionLevel positionLevel;

    @Schema(description = "Employee", example = "Employee")
    @ManyToOne
    @JoinColumn(name = "employee_knowledge_advisor_id", referencedColumnName = "id")
    private EmployeeKnowledgeAdvisor employeeKnowledgeAdvisor;
}