package com.gofit.restController;

import com.gofit.entity.Activities;
import com.gofit.entity.User;
import com.gofit.service.ActivitiesService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/activities")
@CrossOrigin(origins = "http://localhost:3000")
public class ActivitiesController {
    private ActivitiesService activitiesService;

    @Autowired
    ActivitiesController(ActivitiesService activitiesService) {
        this.activitiesService = activitiesService;
    }

    @GetMapping("/")
    ResponseEntity<List<Activities>> getAllActivities() {
        return new ResponseEntity<>(activitiesService.getAllActivities(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    ResponseEntity<Activities> getActivitiesByID(@PathVariable int id) {
        return new ResponseEntity<>(activitiesService.getActivities(id), HttpStatus.OK);
    }

    @PostMapping("/")
    ResponseEntity<Activities> createActivities(@Valid @RequestBody Activities activities) {
        return new ResponseEntity<>(activitiesService.save(activities), HttpStatus.CREATED);
    }

    @PostMapping("/{id}")
    ResponseEntity<Activities> addActivitiesToUser(@Valid @RequestBody Activities activities, @PathVariable int id) {
        return new ResponseEntity<>(activitiesService.addToUser(activities, id), HttpStatus.CREATED);
    }

    @PutMapping("/")
    ResponseEntity<Activities> updateActivitiesToUser(@RequestBody Activities activities) {
        return new ResponseEntity<>(activitiesService.update(activities), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<Activities> deleteActivitiesByID(@PathVariable int id) {
        activitiesService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/deleteAll/{id}")
    ResponseEntity<Activities> deleteAllActivitiesByID(@PathVariable int id) {
        activitiesService.deleteAllFromUser(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
