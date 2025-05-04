package br.com.nttdata.nttskillboost.courseservice.adapters.repository;

import br.com.nttdata.nttskillboost.courseservice.domain.entity.Course;
import br.com.nttdata.nttskillboost.courseservice.ports.out.CourseRepositoryPort;
import br.com.nttdata.nttskillboost.courseservice.ports.out.CourseRepositoryPort;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class CourseRepositoryAdapter implements CourseRepositoryPort {

    private final CourseJpaRepository courseRepository;

    @Override
    public Course save(Course course) {
        return courseRepository.save(course);
    }

    @Override
    public Course findById(UUID id) {
        Optional<Course> byId = courseRepository.findById(id);
        if (byId.isPresent()) {
            return byId.get();
        } else {
            throw new EntityNotFoundException("Funcionário com ID " + id + " não encontrado");
        }
    }

    @Override
    public List<Course> findAll() {
        return courseRepository.findAll();
    }

    @Override
    public Course findByName(String name) {
        return courseRepository.findByName(name);
    }
}
