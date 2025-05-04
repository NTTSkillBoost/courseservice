package br.com.nttdata.nttskillboost.courseservice.ports.in;

import br.com.nttdata.nttskillboost.courseservice.domain.entity.Course;

import java.util.List;
import java.util.UUID;

public interface GetCourseUseCase {
    Course findById(UUID id);
    List<Course> findAll();
}
