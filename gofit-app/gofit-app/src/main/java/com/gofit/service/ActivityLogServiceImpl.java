package com.gofit.service;

import com.gofit.dao.ActivityLogDAO;
import com.gofit.dao.ActivityLogDAOImpl;
import com.gofit.entity.ActivityLog;
import com.gofit.entity.Session;
import com.gofit.entity.User;
import com.gofit.exception.ResourceNotFoundException;
import com.gofit.exception.UnauthorisedOperationException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ActivityLogServiceImpl implements ActivityLogService {
    private final ActivityLogDAO activityLogDAO;
    private final UserService userService;

    @Override
    @Transactional
    public ActivityLog get(int id) {
        User user = userService.getCurrentUser();
        ActivityLog activityLog = activityLogDAO.getActivityLogAndUser(id);
        checkPermission(user, activityLog);
        return activityLog;
    }

    @Override
    @Transactional
    public List<ActivityLog> getAll() {
        User user = userService.getCurrentUser();
        boolean isAdmin = hasRole(user, "ROLE_ADMIN");

        if (isAdmin) {
            return activityLogDAO.findAll();
        } else {
            return null;
        }
    }

    @Override
    @Transactional
    public ActivityLog save(ActivityLog activityLog) {
        User user = userService.getCurrentUser();
        checkPermission(user, activityLog);
        activityLogDAO.save(activityLog);
        return activityLog;
    }

    @Override
    @Transactional
    public ActivityLog update(ActivityLog activityLog) {
        User user = userService.getCurrentUser();
        checkPermission(user, activityLog);

        return activityLogDAO.update(activityLog);
    }

    @Override
    @Transactional
    public void delete(int id) {
        User user = userService.getCurrentUser();
        ActivityLog activityLog = activityLogDAO.getActivityLogAndUser(id);

        if (activityLog == null) {
            throw new ResourceNotFoundException("Activity Log not found");
        }

        checkPermission(user, activityLog);
        activityLogDAO.delete(id);
    }

    private boolean hasRole(User user, String role) {
        return user.getRoles().stream().anyMatch(roles -> roles.getRoleName().equals(role));
    }

    private boolean isOwner(User user, ActivityLog activityLog) {
        return activityLog.getSession().getUser().getId() == user.getId();
    }

    private void checkPermission(User user, ActivityLog activityLog) {
        if (!hasRole(user, "ROLE_ADMIN") && !isOwner(user, activityLog)) {
            throw new UnauthorisedOperationException("You do not have permission to access this resource");
        }
    }


}
