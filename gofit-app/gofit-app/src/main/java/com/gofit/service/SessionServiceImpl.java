package com.gofit.service;

import com.gofit.dao.SessionDAO;
import com.gofit.entity.Activities;
import com.gofit.entity.ActivityLog;
import com.gofit.entity.Session;
import com.gofit.entity.User;
import com.gofit.exception.ResourceNotFoundException;
import com.gofit.exception.UnauthorisedOperationException;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class SessionServiceImpl implements SessionService {

    private final SessionDAO sessionDAO;
    private final ActivityLogService activityLogService;
    private final UserService userService;

    @Override
    @Transactional
    public Session get(int id) {
        Session session = sessionDAO.get(id);
        if (session == null) {
            throw new ResourceNotFoundException("Session not found");
        }

        return session;
    }

    @Override
    @Transactional
    public List<Session> getAll() {
        return sessionDAO.getAll();
    }

    @Override
    @Transactional
    public Session save(@Valid Session session) {
        if (session.getTime() == null) {
            session.setTime(LocalDateTime.now());
        }
        if (session.getActivityLog() == null) {
            session.setActivityLog(new ArrayList<>());
        }
        return sessionDAO.save(session);
    }

    @Override
    @Transactional
    public Session update(@Valid Session session) {
        Session newSession = get(session.getSessionID());
        return sessionDAO.update(newSession);
    }

    @Override
    @Transactional
    public void delete(int id) {
        Session session = get(id); // checks whether the session exists
        session.setUser(null);
        session.setActivityLog(null);
        sessionDAO.delete(id);
    }

    @Override
    @Transactional
    public List<Session> getSessionsByUserId(int userId) {
        User user = userService.getCurrentUser();
        if (user.getId() != userId && !hasRole(user, "ROLE_ADMIN")) {
            throw new UnauthorisedOperationException("You do not have permission to access this resource");
        }

        List<Session> sessions = sessionDAO.getSessionsByUser(userId);
        return sessions;
    }

    @Override
    @Transactional
    public ActivityLog addActivityLog(int sessionID, ActivityLog activityLog) {
        Session session = get(sessionID);
        if (session.getActivityLog() == null) {
            ActivityLog newActivityLog = new ActivityLog();
        }
        session.getActivityLog().add(activityLog);
        return activityLog;
    }

    private boolean checkOwner(User user, Session session) {
        if (session.getUser().getId() != user.getId()) {
            return false;
        }
        return true;
    }

    private boolean hasRole(User user, String role) {
        return user.getRoles().stream().anyMatch(roles -> roles.getRoleName().equals(role));
    }

    private void checkPermission(User user, Session session) {
        if (!checkOwner(user, session) && !hasRole(user, "ROLE_ADMIN")) {
            throw new UnauthorisedOperationException("You do not have permission to perform this operation");
        }
    }
}
