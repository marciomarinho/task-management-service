package au.com.riosoftware.task.repository;

import au.com.riosoftware.task.model.Task;
import org.springframework.data.repository.Repository;

import java.util.List;

public interface TaskRepository extends Repository<Task, Long> {
    Task save(Task task);
    Task findById(long id);
    List<Task> findAll();
    void delete(Task entity);
}