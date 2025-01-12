package com.gofit.service;

import com.gofit.dao.ActivitiesDAO;
import com.gofit.entity.Activities;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import java.util.List;

public interface ActivitiesService {

    List<Activities> getAllActivities();

    Activities getActivities(int id);

    Activities save(Activities activities);

    Activities update(Activities activities);

    void delete(int id);

    Activities addToUser(Activities activities, int userID);

    List<Activities> getAllFromUser(int userID);

    void deleteAllFromUser(int userID);
}
