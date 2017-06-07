package com.balance.service;

import com.balance.model.CaloriesHistory;
import com.balance.repository.CaloriesHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by KEVIN on 05/06/2017.
 */
@Service
public class CaloriesHistoryServiceImpl implements CaloriesHistoryService{

    @Autowired
    CaloriesHistoryRepository caloriesHistoryRepository;

    @Override
    public void saveCaloriesHistory(CaloriesHistory CaloriesHistory) {
        caloriesHistoryRepository.save(CaloriesHistory);
    }

    @Override
    public Iterable<CaloriesHistory> listAllCaloriesHistorys() {
        return caloriesHistoryRepository.findAll();
    }

    @Override
    public CaloriesHistory getCaloriesHistoryById(Integer id) {
        return caloriesHistoryRepository.findOne(id);
    }

    @Override
    public void deleteCaloriesHistory(Integer id) {
        caloriesHistoryRepository.delete(id);
    }
}
