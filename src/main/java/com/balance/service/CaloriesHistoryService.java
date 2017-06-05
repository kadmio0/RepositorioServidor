package com.balance.service;

import com.balance.model.CaloriesHistory;

/**
 * Created by KEVIN on 05/06/2017.
 */
public interface CaloriesHistoryService {
    void saveCaloriesHistory(CaloriesHistory CaloriesHistory);
    Iterable<CaloriesHistory> listAllCaloriesHistorys();
    CaloriesHistory getCaloriesHistoryById(Integer id);
    void deleteCaloriesHistory(Integer id);
}
