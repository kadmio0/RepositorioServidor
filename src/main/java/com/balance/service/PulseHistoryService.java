package com.balance.service;

import com.balance.model.PulseHistory;

/**
 * Created by da_20 on 31/5/2017.
 */
public interface PulseHistoryService {
    void savePulseHistory(PulseHistory pulseHistory);
    Iterable<PulseHistory> listAllPulseHistory();
    PulseHistory getPulseHistoryById(Integer id);
    void deletePulseHistory(Integer id);
}
