package com.balance.service;

import com.balance.model.PulseHistory;
import com.balance.repository.PulseHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by rodri on 6/5/2017.
 */
@Service
public class PulseHistoryServiceImpl implements  PulseHistoryService{

    @Autowired
    private PulseHistoryRepository pulseHistoryRepository;

    @Override
    public void savePulseHistory(PulseHistory pulseHistory) {
        pulseHistoryRepository.save(pulseHistory);
    }

    @Override
    public Iterable<PulseHistory> listAllPulseHistory() {
        return pulseHistoryRepository.findAll();
    }

    @Override
    public PulseHistory getPulseHistoryById(Integer id) {
        return pulseHistoryRepository.findOne(id);
    }

    @Override
    public void deletePulseHistory(Integer id) {
        pulseHistoryRepository.delete(id);
    }
}

