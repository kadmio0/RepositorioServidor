package com.balance.service;

import com.balance.model.StepsHistory;
import com.balance.repository.StepsHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by da_20 on 5/6/2017.
 */
@Service
public class StepsHistoryServiceImpl implements StepsHistoryService{

    @Autowired
    private StepsHistoryRepository stepsHistoryRepository;

    @Override
    public void saveStepsHistory(StepsHistory stepsHistory) {
        stepsHistoryRepository.save(stepsHistory);
    }

    @Override
    public Iterable<StepsHistory> listAllStepsHistory() {
        return stepsHistoryRepository.findAll();
    }

    @Override
    public StepsHistory getStepsHistoryById(Integer id) {
        return stepsHistoryRepository.findOne(id);
    }

    @Override
    public void deleteStepsHistory(Integer id) {
        stepsHistoryRepository.delete(id);
    }
}
