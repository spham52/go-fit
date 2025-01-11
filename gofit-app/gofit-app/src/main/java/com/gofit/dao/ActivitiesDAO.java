package com.gofit.dao;

import com.gofit.entity.Activities;

import java.util.List;

public interface ActivitiesDAO {

    List<Activities> getAll();

    Activities get(int id);

    Activities save(Activities activities);

    Activities update(Activities activities);

    void delete(int id);
}
