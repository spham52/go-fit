package com.gofit.restController;

import com.gofit.entity.User;
import com.gofit.exception.ResourceNotFoundException;
import com.gofit.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("/api/users")
@CrossOrigin(origins = "http://localhost:3000")
@Validated
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
            throw new ResourceNotFoundException("User with id " + id + " not found");
        }
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping()
    ResponseEntity<User> addUser(@Valid @RequestBody User user) {
        user.setId(0);
        userService.save(user);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @PutMapping()
    ResponseEntity<User> updateUser(@Valid @RequestBody User user) {
        User updatedUser = userService.findByID(user.getId());
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<User> deleteUser(@PathVariable("id") int id) {
        User user = userService.findByID(id);
        userService.deleteByID(user.getId());
        return new ResponseEntity<>(user, HttpStatus.NO_CONTENT);
    }
}
