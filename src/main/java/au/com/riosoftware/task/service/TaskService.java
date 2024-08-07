package au.com.riosoftware.task.service;

import au.com.riosoftware.task.model.Task;
import au.com.riosoftware.task.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    private TaskRepository taskRepository;

    @Autowired
    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public Task createTask(Task task) {
        return this.taskRepository.save(task);
    }

    public Optional<List<Task>> getAllTasks() {
        return this.taskRepository.findAll();
    }
}
