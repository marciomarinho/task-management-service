package au.com.riosoftware.task.service;

import au.com.riosoftware.task.model.Task;
import au.com.riosoftware.task.repository.TaskRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.mockito.Mockito.*;

class TaskServiceTest {

    private TaskService taskService;
    private TaskRepository taskRepository;

    @BeforeEach
    void setUp() {
        taskRepository = mock(TaskRepository.class);
        taskService = new TaskService(taskRepository);
    }

    @Test
    void shouldSaveTask() {
        Task task = new Task(1l, "First Task", "Nothing too complicated");
        taskService.createTask(task);
        verify(taskRepository, times(1)).save(task);
    }

    @Test
    void shouldGetAllTasks() {
        taskService.getAllTasks();
        verify(taskRepository, times(1)).findAll();
    }

}