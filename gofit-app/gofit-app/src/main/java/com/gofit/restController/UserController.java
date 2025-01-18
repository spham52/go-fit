package com.gofit.restController;

import com.gofit.entity.User;
import com.gofit.exception.ResourceNotFound;
import com.gofit.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("/api/users")
public class UserController {
    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.findAllUsers();
        if (users.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(userService.findAllUsers(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    ResponseEntity<User> getUserById(@PathVariable("id") int id) {
        User user = userService.findByID(id);
        if (user == null) {
            throw new ResourceNotFound("User with id " + id + " not found");
        }
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping()
    User addUser(@Valid @RequestBody User user) {
        user.setId(0);
        return userService.save(user);
    }

    @PutMapping()
    User updateUser(@Valid @RequestBody User user) {
        return userService.update(user);
    }

    @DeleteMapping("/{id}")
    void deleteUser(@PathVariable("id") int id) {
        User user = userService.findByID(id);
        if (user == null) {
            throw new ResourceNotFound("User with id " + id + " not found");
        }
        userService.deleteByID(id);
    }
}
