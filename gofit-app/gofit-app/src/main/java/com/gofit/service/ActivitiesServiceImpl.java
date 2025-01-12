package com.gofit.service;

import com.gofit.dao.ActivitiesDAO;
import com.gofit.entity.Activities;
import com.gofit.entity.User;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
    public List<Activities> getAllActivities() {
        return activitiesDAO.getAll();
    }

    @Override
    public Activities getActivities(int id) {
        try {
            return activitiesDAO.get(id);
        } catch (IllegalArgumentException e) {
            System.out.println("Activity not found");
            return null;
        }
    }

    @Override
    @Transactional
    public Activities save(Activities activities) {
        return activitiesDAO.save(activities);
    }

    @Override
    @Transactional
    public Activities update(Activities activities) {
        return activitiesDAO.update(activities);
    }

    @Override
    @Transactional
    public void delete(int id) {
        Activities activities = activitiesDAO.get(id);
        if (activities.isCustom()) {
            User user = activities.getUser();
            user.getActivities().remove(activities);
            activities.setUser(null);
        }

        activitiesDAO.delete(id);
    }

    @Override
    @Transactional
    public Activities addToUser(Activities activities, int userID) {
        User user = userService.findByID(userID);

        if (user.getActivities() == null) {
            user.setActivities(new ArrayList<>());
        }

        // activity is a custom activity added by the user
        activities.setCustom(true);
        user.getActivities().add(activities);
        activities.setUser(user);
        return activitiesDAO.save(activities);
    }

    @Override
    public List<Activities> getAllFromUser(int userID) {
        return activitiesDAO.getAllFromUser(userID);
    }

    @Override
    @Transactional
    public void deleteAllFromUser(int userID) {
        activitiesDAO.deleteAllActivitiesFromUser(userID);
    }

}
