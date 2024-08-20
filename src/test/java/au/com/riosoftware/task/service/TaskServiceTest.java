package au.com.riosoftware.task.service;

import au.com.riosoftware.task.model.Task;
import au.com.riosoftware.task.repository.TaskRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
    void shouldCreateTask() {
        Task task = new Task(1l, "First Task", "Nothing too complicated");
        taskService.createTask(task);
        verify(taskRepository, times(1)).save(task);
    }

    @Test
    void shouldGetAllTasks() {
        taskService.getAllTasks();
        verify(taskRepository, times(1)).findAll();
        verifyNoMoreInteractions(taskRepository);
    }

    @Test
    void shouldFindTaskById() {
        taskService.findTaskById(1l);
        taskService.findTaskById(2l);
        taskService.findTaskById(3l);
        verify(taskRepository, times(1)).findById(1);
        verify(taskRepository, times(1)).findById(2);
        verify(taskRepository, times(1)).findById(3);
        verifyNoMoreInteractions(taskRepository);
    }

    @Test
    void shouldDeleteTask() {
        Task task = new Task(1l, "", "");
        taskService.deleteTask(task);
        verify(taskRepository, times(1)).delete(task);
        verifyNoMoreInteractions(taskRepository);
    }

}