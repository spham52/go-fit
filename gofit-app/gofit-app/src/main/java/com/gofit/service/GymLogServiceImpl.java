package com.gofit.service;

import com.gofit.dao.ActivityLogDAO;
import com.gofit.dao.GymLogDAO;
import com.gofit.entity.ActivityLog;
import com.gofit.entity.CardioLog;
import com.gofit.entity.GymLog;
import com.gofit.entity.User;
import com.gofit.exception.ResourceNotFoundException;
import com.gofit.exception.UnauthorisedOperationException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
public class GymLogServiceImpl implements GymLogService {
    private final GymLogDAO gymLogDAO;
    private final ActivityLogService activityLogService;
    private final UserService userService;

    @Override
    public GymLog getGymLog(int id) {
        GymLog gymLog = gymLogDAO.get(id);
        User user = userService.getCurrentUser();
        if (gymLog == null) {
            throw new ResourceNotFoundException("This gym log does not exist.");
        }
        checkPermission(user, gymLog);
        return gymLog;
    }

    @Override
    public GymLog save(GymLog gymLog) {


        checkPermission(userService.getCurrentUser(), gymLog);
        return null;
    }

    @Override
    public GymLog update(GymLog gymLog) {
        ActivityLog activityLog = gymLog.getActivityLog();

        if (activityLog == null) {
            throw new IllegalArgumentException();
        }

        checkPermission(userService.getCurrentUser(), gymLog);
        return gymLogDAO.update(gymLog);
    }

    @Override
    public void delete(int id) {
        GymLog gymLog = gymLogDAO.get(id);
        if (gymLog == null) {
            throw new ResourceNotFoundException("This gym log does not exist.");
        }

        checkPermission(userService.getCurrentUser(), gymLog);
        gymLogDAO.delete(id);
    }

    private boolean checkOwner(User user, GymLog gymLog) {
        ActivityLog activityLog = activityLogService.
                getActivityLogAndUser(gymLog.getActivityLog().getActivityLogId());

        return user.getId() == activityLog.getSession().getUser().getId();
    }

    private boolean hasRole(User user, String role) {
        return user.getRoles().stream().anyMatch(roles -> roles.getRoleName().equals(role));
    }

    private void checkPermission(User user, GymLog gymLog) {
        if (!hasRole(user, "ROLE_ADMIN") && !checkOwner(user, gymLog)) {
            throw new UnauthorisedOperationException("You do not have permission to perform this operation");
        }
    }
}
