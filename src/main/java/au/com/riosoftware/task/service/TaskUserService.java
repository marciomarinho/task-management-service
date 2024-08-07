package au.com.riosoftware.task.service;

import au.com.riosoftware.task.controller.request.UserTask;
import au.com.riosoftware.task.model.Task;
import au.com.riosoftware.task.model.User;
import au.com.riosoftware.task.repository.TaskRepository;
import au.com.riosoftware.task.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TaskUserService {

    private TaskRepository taskRepository;
    private UserRepository userRepository;

    @Autowired
    public TaskUserService(TaskRepository taskRepository, UserRepository userRepository) {
        this.taskRepository = taskRepository;
        this.userRepository = userRepository;
    }

    public Task assignUserToTask(UserTask userTask) {
        Task task = taskRepository.findById(userTask.taskId());
        User user = userRepository.findById(userTask.userId());
        task.setUser(user);
        return this.taskRepository.save(task);
    }

}
