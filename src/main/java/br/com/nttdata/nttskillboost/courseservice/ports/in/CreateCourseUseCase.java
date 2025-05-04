package br.com.nttdata.nttskillboost.courseservice.ports.in;

import br.com.nttdata.nttskillboost.courseservice.domain.entity.Course;

public interface CreateCourseUseCase {
    Course create(Course course);
}
