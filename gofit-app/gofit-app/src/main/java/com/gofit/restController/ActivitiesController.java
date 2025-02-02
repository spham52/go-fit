package com.gofit.restController;

import com.gofit.entity.Activities;
import com.gofit.entity.User;
import com.gofit.service.ActivitiesService;
import com.gofit.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/activities")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class ActivitiesController {
    private final ActivitiesService activitiesService;
    private final UserService userService;

    @GetMapping("")
    ResponseEntity<List<Activities>> getAllActivities() {
        return new ResponseEntity<>(activitiesService.getAllActivities(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    ResponseEntity<Activities> getActivitiesByID(@PathVariable int id) {
        return new ResponseEntity<>(activitiesService.getActivities(id), HttpStatus.OK);
    }

    @PostMapping("")
    ResponseEntity<Activities> createActivities(@Valid @RequestBody Activities activities) {
        User user = userService.getCurrentUser();
        activities.setUser(user);
        return new ResponseEntity<>(activitiesService.save(activities), HttpStatus.CREATED);
    }

    @PutMapping("")
    ResponseEntity<Activities> updateActivities(@RequestBody Activities activities) {
        return new ResponseEntity<>(activitiesService.update(activities), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<Activities> deleteActivitiesByID(@PathVariable int id) {
        activitiesService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
