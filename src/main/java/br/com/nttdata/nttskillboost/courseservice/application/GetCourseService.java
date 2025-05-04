package br.com.nttdata.nttskillboost.courseservice.application;

import br.com.nttdata.nttskillboost.courseservice.domain.entity.Course;
import br.com.nttdata.nttskillboost.courseservice.ports.in.GetCourseUseCase;
import br.com.nttdata.nttskillboost.courseservice.ports.out.CourseRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class GetCourseService implements GetCourseUseCase {

    private final CourseRepositoryPort courseRepositoryPort;

    @Override
    public Course findById(UUID id) {
        return courseRepositoryPort.findById(id);
    }

    @Override
    public List<Course> findAll() {
        return courseRepositoryPort.findAll();
    }
}
