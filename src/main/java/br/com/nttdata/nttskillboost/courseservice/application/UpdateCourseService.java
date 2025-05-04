package br.com.nttdata.nttskillboost.courseservice.application;

import br.com.nttdata.nttskillboost.courseservice.domain.entity.Course;
import br.com.nttdata.nttskillboost.courseservice.domain.entity.EmployeeKnowledgeAdvisor;
import br.com.nttdata.nttskillboost.courseservice.ports.in.UpdateCourseUseCase;
import br.com.nttdata.nttskillboost.courseservice.ports.out.CourseRepositoryPort;
import br.com.nttdata.nttskillboost.courseservice.ports.out.EmployeeKnowledgeAdvisorRepositoryPort;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UpdateCourseService implements UpdateCourseUseCase {

    private final CourseRepositoryPort courseRepositoryPort;
    private final EmployeeKnowledgeAdvisorRepositoryPort employeeKnowledgeAdvisorRepositoryPort;

    @Override
    public Course update(UUID id, Course course) {
        Course byId = courseRepositoryPort.findById(id);
        if (byId == null) {
            return null;
        }

        Course courseUpdate = new Course();

        EmployeeKnowledgeAdvisor employeeKnowledgeAdvisor = employeeKnowledgeAdvisorRepositoryPort.findById(course.getEmployeeKnowledgeAdvisor().getId());
        if (employeeKnowledgeAdvisor == null) {
            throw new EntityNotFoundException("Funcionário não encontrado");
        }

        courseUpdate.setId(byId.getId());
        courseUpdate.setName(course.getName());
        courseUpdate.setDescription(course.getDescription());
        courseUpdate.setSyllabus(course.getSyllabus());
        courseUpdate.setGoalPoints(course.getGoalPoints());
        courseUpdate.setMakeDate(course.getMakeDate());
        courseUpdate.setCourseStatus(course.getCourseStatus());
        courseUpdate.setPositionLevel(course.getPositionLevel());
        courseUpdate.setEmployeeKnowledgeAdvisor(employeeKnowledgeAdvisor);

        return courseRepositoryPort.save(courseUpdate);
    }
}
