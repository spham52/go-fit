package com.gofit.dao;

import com.gofit.entity.ActivityLog;
import com.gofit.entity.Roles;

import java.util.List;

public interface ActivityLogDAO {

    List<ActivityLog> findAll();

    ActivityLog findById(int id);

    ActivityLog save(ActivityLog activityLog);

    ActivityLog update(ActivityLog activityLog);

    void delete(int id);

    ActivityLog getActivityLogAndUser(int activityLogID);

}
