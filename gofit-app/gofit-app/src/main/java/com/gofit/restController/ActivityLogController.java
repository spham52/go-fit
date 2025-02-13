package com.gofit.restController;

import com.gofit.entity.ActivityLog;
import com.gofit.entity.CardioLog;
import com.gofit.entity.GymLog;
import com.gofit.service.ActivityLogService;
import com.gofit.service.CardioLogService;
import com.gofit.service.GymLogService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/activity_log")
@CrossOrigin(origins = "http://localhost:3000")
@RequiredArgsConstructor
public class ActivityLogController {

    private final ActivityLogService activityLogService;
    private final CardioLogService cardioLogService;
    private final GymLogService gymLogService;

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

    // MORE INFORMATION ABOUT ACTIVITY LOG DETAILS
    // ------------------------------

    @GetMapping("/{id}/details")
    public ResponseEntity<?> details(@PathVariable int id) {
        ActivityLog activityLog = activityLogService.get(id);

        if (activityLog.getActivities().getActivityType().equalsIgnoreCase("cardio")) {
            CardioLog cardioLog = cardioLogService.getCardioLog(id);
            return ResponseEntity.ok(cardioLog);
        } else if (activityLog.getActivities().getActivityType().equalsIgnoreCase("gym")) {
            GymLog gymLog = gymLogService.getGymLog(id);
            return ResponseEntity.ok(gymLog);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
}
