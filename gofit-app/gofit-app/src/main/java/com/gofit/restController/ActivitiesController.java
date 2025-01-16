package com.gofit.restController;

import com.gofit.entity.Activities;
import com.gofit.service.ActivitiesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/activities")
public class ActivitiesController {
    private ActivitiesService activitiesService;

    @Autowired
    public ActivitiesController(ActivitiesService activitiesService) {
        this.activitiesService = activitiesService;
    }

    @GetMapping("/")
    public List<Activities> getAllActivities() {
        return activitiesService.getAllActivities();
    }

    @GetMapping("/{id}")
    public Activities getActivitiesByID(@PathVariable int id) {
        return activitiesService.getActivities(id);
    }

    @PostMapping("/")
    public Activities createActivities(@RequestBody Activities activities) {
        return activitiesService.save(activities);
    }

    @PostMapping("/{id}")
    Activities addActivitiesToUser(@RequestBody Activities activities, @PathVariable int id) {
        return activitiesService.addToUser(activities, id);
    }

    @PutMapping("/")
    Activities updateActivitiesToUser(@RequestBody Activities activities) {
        return activitiesService.update(activities);
    }

    @DeleteMapping("/{id}")
    void deleteActivitiesByID(@PathVariable int id) {
        activitiesService.delete(id);
    }

    @DeleteMapping("/deleteAll/{id}")
    void deleteAllActivitiesByID(@PathVariable int id) {
        activitiesService.deleteAllFromUser(id);
    }
}
