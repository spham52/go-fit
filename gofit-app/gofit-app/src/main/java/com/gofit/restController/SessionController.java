package com.gofit.restController;

import com.gofit.dto.ActivityLogDTO;
import com.gofit.entity.Activities;
import com.gofit.entity.ActivityLog;
import com.gofit.entity.Session;
import com.gofit.entity.User;
import com.gofit.service.ActivitiesService;
import com.gofit.service.SessionService;
import com.gofit.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/session")
@CrossOrigin(origins = "http://localhost:3000")
@RequiredArgsConstructor
public class SessionController {

    private final SessionService sessionService;
    private final UserService userService;
    private final ActivitiesService activitiesService;

    @GetMapping("/{id}")
    public Session getSession(@PathVariable int id) {
        return sessionService.get(id);
    }

    @GetMapping("/user/{userID}")
    public List<Session> getAllSessionsFromUser(@PathVariable int userID) {
        return sessionService.getSessionsByUserId(userID);
    }

    @PostMapping()
    public Session createSession(@RequestBody Session session) {
        User user = userService.getCurrentUser();
        session.setUser(user);
        return sessionService.save(session);
    }

    @PostMapping("/{sessionID}")
    public Session addActivityLogToSession(@PathVariable int sessionID, @RequestBody ActivityLogDTO dto) {
        Session session = sessionService.get(sessionID);
        Activities activity = activitiesService.getActivities(dto.getActivityID());

        ActivityLog activityLog = new ActivityLog();
        activityLog.setActivities(activity);
        activityLog.setSession(session);

        if (session.getActivityLog() == null) {
            session.setActivityLog(new ArrayList<>());
        }
        session.getActivityLog().add(activityLog);

        return sessionService.save(session);
    }

    @PutMapping
    public Session updateSession(@RequestBody Session session) {
        return sessionService.update(session);
    }

    @DeleteMapping
    public void deleteSession(@PathVariable int id) {
        sessionService.delete(id);
    }
}
