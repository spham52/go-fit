package com.gofit.service;

import com.gofit.entity.ActivityLog;
import com.gofit.entity.Session;

import java.util.List;

public interface ActivityLogService {

    ActivityLog get(int id);

    List<ActivityLog> getAll();

    ActivityLog save(ActivityLog activityLog);

    ActivityLog update(ActivityLog activityLog);

    void delete(int id);
}
