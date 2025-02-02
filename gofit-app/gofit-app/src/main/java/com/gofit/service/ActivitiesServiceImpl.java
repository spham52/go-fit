package com.gofit.service;

import com.gofit.dao.ActivitiesDAO;
import com.gofit.entity.Activities;
import com.gofit.entity.User;
import com.gofit.exception.ResourceNotFoundException;
import com.gofit.exception.UnauthorisedOperationException;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ActivitiesServiceImpl implements ActivitiesService {

    private ActivitiesDAO activitiesDAO;
    private UserService userService;

    @Autowired
    public ActivitiesServiceImpl(ActivitiesDAO activitiesDAO, UserService userService) {
        this.activitiesDAO = activitiesDAO;
        this.userService = userService;
    }

    @Override
    @Transactional
    public List<Activities> getAllActivities() {
        return activitiesDAO.getAll();
    }

    @Override
    @Transactional
    public Activities getActivities(int id) {
        User user = userService.getCurrentUser();
        Activities activities = activitiesDAO.get(id);

        if (activities == null) {
            throw new ResourceNotFoundException("Activities not found");
        }

        checkPermission(user, activities);
        return activities;
    }

    @Override
    @Transactional
    public Activities save(@Valid Activities activities) {
        User user = userService.getCurrentUser();
        return activitiesDAO.save(activities);
    }

    @Override
    @Transactional
    public Activities update(@Valid Activities activities) {
        User user = userService.getCurrentUser();
        return activitiesDAO.update(activities);
    }

    @Override
    @Transactional
    public void delete(int id) {
        User user = userService.getCurrentUser();
        Activities activity = activitiesDAO.getActivitiesAndUser(id);
        checkPermission(user, activity);
        activitiesDAO.delete(id);
    }

    @Override
    public List<Activities> getAllFromUser() {
        User user = userService.getCurrentUser();
        List<Activities> list = activitiesDAO.getAllFromUser(user.getId());
        list.addAll(activitiesDAO.getDefaultActivities());
        return list;
    }

    private boolean hasRole(User user, String roleName) {
        return user.getRoles().stream().anyMatch(role -> role.getRoleName().equals(roleName));
    }

    private boolean isOwner(User user, Activities activity) {
        if (activity.getUser().getId() != user.getId()) {
            return false;
        }
        return true;
    }

    private boolean checkPermission(User user, Activities activity) {
        if (!isOwner(user, activity) && !hasRole(user, "ROLE_ADMIN")) {
            throw new UnauthorisedOperationException("You do not have permission to access this resource");
        }
        return true;
    }
}
