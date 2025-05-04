package br.com.nttdata.nttskillboost.courseservice.ports.in;

import java.util.UUID;

public interface DeleteCourseUseCase {
    boolean delete(UUID id);
}
