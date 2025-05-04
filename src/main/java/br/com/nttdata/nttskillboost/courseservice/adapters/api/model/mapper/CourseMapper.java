package br.com.nttdata.nttskillboost.courseservice.adapters.api.model.mapper;

import br.com.nttdata.nttskillboost.courseservice.adapters.api.model.dto.CourseRequest;
import br.com.nttdata.nttskillboost.courseservice.adapters.api.model.dto.CourseResponse;
import br.com.nttdata.nttskillboost.courseservice.domain.entity.Course;
import br.com.nttdata.nttskillboost.courseservice.domain.entity.CourseStatus;
import br.com.nttdata.nttskillboost.courseservice.domain.entity.EmployeeKnowledgeAdvisor;
import br.com.nttdata.nttskillboost.courseservice.domain.entity.PositionLevel;
import org.springframework.stereotype.Component;

@Component
public class CourseMapper {

    public Course toDomain(CourseRequest dto) {
        return Course.builder()
                .name(dto.getName())
                .description(dto.getDescription())
                .syllabus(dto.getSyllabus())
                .goalPoints(dto.getGoalPoints())
                .makeDate(dto.getMakeDate())
                .courseStatus(CourseStatus.getCourseStatus(dto.getCourseStatus()))
                .positionLevel(PositionLevel.getPositionLevel(dto.getPositionLevel()))
                .employeeKnowledgeAdvisor(EmployeeKnowledgeAdvisor.builder().id(dto.getEmployeeKnowledgeAdvisorId()).build())
                .build();
    }

    public CourseResponse toResponse(Course course) {
        return CourseResponse.builder()
                .id(course.getId())
                .name(course.getName())
                .description(course.getDescription())
                .syllabus(course.getSyllabus())
                .goalPoints(course.getGoalPoints())
                .makeDate(course.getMakeDate())
                .courseStatus(course.getCourseStatus().getDescription())
                .positionLevel(course.getPositionLevel().getDescription())
                .employeeKnowledgeAdvisorId(course.getEmployeeKnowledgeAdvisor().getId())
                .build();
    }
}