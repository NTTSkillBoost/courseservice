package br.com.nttdata.nttskillboost.courseservice.ports.out;

import br.com.nttdata.nttskillboost.courseservice.domain.entity.Course;

import java.util.List;
import java.util.UUID;

public interface CourseRepositoryPort {
    Course save(Course course);
    Course findById(UUID id);
    List<Course> findAll();
    Course findByName(String name);
}
