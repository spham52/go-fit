package com.gofit.controller;

import com.gofit.entity.User;
import com.gofit.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {
    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    List<User> getAllUsers() {
        return userService.findAllUsers();
    }

    @GetMapping("/users/{id}")
    User getUserById(@PathVariable("id") int id) {
        return userService.findByID(id);
    }

    @PostMapping("/users")
    User addUser(@RequestBody User user) {
        user.setId(0);
        return userService.save(user);
    }

    @PutMapping("/users")
    User updateUser(@RequestBody User user) {
        return userService.update(user);
    }

    @DeleteMapping("/users/{id}")
    void deleteUser(@PathVariable("id") int id) {
        userService.deleteByID(id);
    }
}
