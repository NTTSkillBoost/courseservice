package br.com.nttdata.nttskillboost.courseservice.adapters.repository;

import br.com.nttdata.nttskillboost.courseservice.domain.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CourseJpaRepository extends JpaRepository<Course, UUID> {
    Course findByName(String name);
}
