package com.chakray.userapi.controller;

import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chakray.userapi.models.User;
import com.chakray.userapi.service.UserService;

@RestController
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public List<User> getAllUsers() {
        // Get the users from the service
        var users = userService.getAllUsers();
        return users;
    }

}
