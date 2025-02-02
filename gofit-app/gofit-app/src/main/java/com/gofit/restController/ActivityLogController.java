package com.gofit.restController;

import com.gofit.entity.ActivityLog;
import com.gofit.service.ActivityLogService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/activity_log")
@CrossOrigin(origins = "http://localhost:3000")
@RequiredArgsConstructor
public class ActivityLogController {

    private final ActivityLogService activityLogService;

    @GetMapping("/{id}")
    public ActivityLog getActivityLog(@PathVariable int id) {
        return activityLogService.get(id);
    }

    @PutMapping
    public ActivityLog updateActivityLog(@RequestBody ActivityLog activityLog) {
        return activityLogService.update(activityLog);
    }

    @PostMapping
    public ActivityLog addActivityLog(@RequestBody ActivityLog activityLog) {
        return activityLogService.save(activityLog);
    }

    @DeleteMapping("/{id}")
    public void deleteActivityLog(@PathVariable int id) {
        activityLogService.delete(id);
    }
}
