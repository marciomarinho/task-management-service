package au.com.riosoftware.task.repository;

import au.com.riosoftware.task.model.Task;
import org.springframework.data.repository.Repository;

import java.util.List;
import java.util.Optional;

public interface TaskRepository extends Repository<Task, Long> {
    Task save(Task person);
    Optional<Task> findById(long id);
    Optional<List<Task>> findAll();
}