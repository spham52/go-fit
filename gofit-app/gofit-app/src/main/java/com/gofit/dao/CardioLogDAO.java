package com.gofit.dao;

import com.gofit.entity.CardioLog;

import java.util.Optional;

public interface CardioLogDAO {

    CardioLog get(int id);

    CardioLog save(CardioLog cardioLog);

    void delete(int id);

    CardioLog update(CardioLog cardioLog);

    Optional<CardioLog> getCardioLogByActivityID(int activityID);
}
