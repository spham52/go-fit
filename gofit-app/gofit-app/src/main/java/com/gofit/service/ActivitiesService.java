package com.gofit.service;

import com.gofit.entity.Activities;
import com.gofit.entity.User;

import java.util.List;

public interface ActivitiesService {

    List<Activities> getAllActivities();

    Activities getActivities(int id);

    Activities save(Activities activities);

    Activities update(Activities activities);

    void delete(int id);

    List<Activities> getAllFromUser();
}
