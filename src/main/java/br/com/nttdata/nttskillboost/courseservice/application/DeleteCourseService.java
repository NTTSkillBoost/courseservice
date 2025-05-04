package br.com.nttdata.nttskillboost.courseservice.application;

import br.com.nttdata.nttskillboost.courseservice.domain.entity.Course;
import br.com.nttdata.nttskillboost.courseservice.domain.entity.Status;
import br.com.nttdata.nttskillboost.courseservice.ports.in.DeleteCourseUseCase;
import br.com.nttdata.nttskillboost.courseservice.ports.out.CourseRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DeleteCourseService implements DeleteCourseUseCase {

    private final CourseRepositoryPort courseRepositoryPort;

    @Override
    public boolean delete(UUID id) {
        Course byId = courseRepositoryPort.findById(id);
        if (byId != null) {
            byId.setStatus(Status.DELETED);
            courseRepositoryPort.save(byId);
            return true;
        } else {
            return false;
        }
    }
}
