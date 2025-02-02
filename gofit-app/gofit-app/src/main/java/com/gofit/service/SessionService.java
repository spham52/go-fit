package com.gofit.service;

import com.gofit.entity.ActivityLog;
import com.gofit.entity.Session;

import java.util.List;

public interface SessionService {

    Session get(int id);

    List<Session> getAll();

    Session save(Session session);

    Session update(Session session);

    void delete(int id);

    List<Session> getSessionsByUserId(int userId);

    ActivityLog addActivityLog(int sessionID, ActivityLog activityLog);
}
