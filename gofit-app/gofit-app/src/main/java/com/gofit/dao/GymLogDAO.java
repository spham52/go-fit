package com.gofit.dao;

import com.gofit.entity.GymLog;

public interface GymLogDAO {

    GymLog get(int id);

    GymLog save(GymLog gymLog);

    GymLog update(GymLog gymLog);

    void delete(int id);
}
