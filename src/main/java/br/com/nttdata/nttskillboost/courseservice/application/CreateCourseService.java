package br.com.nttdata.nttskillboost.courseservice.application;

import br.com.nttdata.nttskillboost.courseservice.domain.entity.Course;
import br.com.nttdata.nttskillboost.courseservice.ports.in.CreateCourseUseCase;
import br.com.nttdata.nttskillboost.courseservice.ports.out.CourseRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateCourseService implements CreateCourseUseCase {

    private final CourseRepositoryPort employeeRepositoryPort;

    @Override
    public Course create(Course course) {
        if (employeeRepositoryPort.findByName(course.getName()) != null) {
            throw new IllegalArgumentException("Já existe funcionário com este email.");
        }

        return employeeRepositoryPort.save(course);
    }
}
