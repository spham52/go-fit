package com.gofit.service;

import com.gofit.entity.CardioLog;

public interface CardioLogService {

    CardioLog getCardioLog(int id);

    CardioLog saveCardioLog(CardioLog cardioLog);

    CardioLog updateCardioLog(CardioLog cardioLog);

    void deleteCardioLog(int id);
}
