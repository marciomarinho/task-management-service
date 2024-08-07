package au.com.riosoftware.task.controller;

import au.com.riosoftware.task.controller.request.UserTask;
import au.com.riosoftware.task.model.Task;
import au.com.riosoftware.task.service.TaskService;
import au.com.riosoftware.task.service.TaskUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/tasks")
public class TaskController {

    private TaskService taskService;
    private TaskUserService taskUserService;

    @Autowired
    public TaskController(TaskService taskService, TaskUserService taskUserService) {
        this.taskService = taskService;
        this.taskUserService = taskUserService;
    }

    @PostMapping
    public Task createTask(@RequestBody Task task) {
        return taskService.createTask(task);
    }

    @GetMapping
    public List<Task> getAllTasks() {
        return taskService.getAllTasks();
    }

    @PatchMapping
    public Task assignUserToTask(@RequestBody UserTask userTask) {
        return taskUserService.assignUserToTask(userTask);
    }
}
