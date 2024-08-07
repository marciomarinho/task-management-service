package au.com.riosoftware.task.controller;

import au.com.riosoftware.task.model.User;
import au.com.riosoftware.task.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/users")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public User createUser(@RequestBody User user) {
        return userService.createTask(user);
    }

    @GetMapping
    public List<User> getAllTasks() {
        return userService.getAllUsers();
    }

}
