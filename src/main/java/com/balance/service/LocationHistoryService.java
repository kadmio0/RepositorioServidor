package com.balance.service;

import com.balance.model.LocationHistory;

/**
 * Created by da_20 on 5/6/2017.
 */
public interface LocationHistoryService {
    void saveLocationHistory(LocationHistory locationHistory);
    Iterable<LocationHistory> listAllLocationHistory();
    LocationHistory getLocationHistoryById(Integer id);
    void deleteLocationHistory(Integer id);
}
