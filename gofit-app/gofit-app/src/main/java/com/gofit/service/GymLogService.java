package com.gofit.service;

import com.gofit.entity.GymLog;

public interface GymLogService {

    GymLog getGymLog(int id);

    GymLog save(GymLog gymLog);

    GymLog update(GymLog gymLog);

    void delete(int id);
}
