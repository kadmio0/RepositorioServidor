package com.balance.service;

import com.balance.model.StepsHistory;

/**
 * Created by da_20 on 5/6/2017.
 */
public interface StepsHistoryService {
    void saveStepsHistory(StepsHistory stepsHistory);
    Iterable<StepsHistory> listAllStepsHistory();
    StepsHistory getStepsHistoryById(Integer id);
    void deleteStepsHistory(Integer id);
}
