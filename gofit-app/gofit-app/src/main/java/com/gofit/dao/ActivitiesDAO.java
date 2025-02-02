package com.gofit.dao;

import com.gofit.entity.Activities;
import org.springframework.security.core.userdetails.User;

import java.util.List;

public interface ActivitiesDAO {

    List<Activities> getAll();

    Activities get(int id);

    Activities save(Activities activities);

    Activities update(Activities activities);

    void delete(int id);

    List<Activities> getAllFromUser(int userID);

    void deleteAllActivitiesFromUser(int userID);

    List<Activities> getAllActivitiesFromUser(int userID);

    List<Activities> getDefaultActivities();

    Activities getActivitiesAndUser(int id);
}
