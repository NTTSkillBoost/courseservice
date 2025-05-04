package br.com.nttdata.nttskillboost.courseservice.ports.in;

import br.com.nttdata.nttskillboost.courseservice.domain.entity.Course;

import java.util.UUID;

public interface UpdateCourseUseCase {
    Course update(UUID id, Course employeeRole);
}
