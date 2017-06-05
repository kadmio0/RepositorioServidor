package com.balance.service;

import com.balance.model.LocationHistory;
import com.balance.repository.LocationHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by da_20 on 5/6/2017.
 */
@Service
public class LocationHistoryServiceImpl implements LocationHistoryService{

    @Autowired
    private LocationHistoryRepository locationHistoryRepository;

    @Override
    public void saveLocationHistory(LocationHistory locationHistory) {
        locationHistoryRepository.save(locationHistory);
    }

    @Override
    public Iterable<LocationHistory> listAllLocationHistory() {
        return locationHistoryRepository.findAll();
    }

    @Override
    public LocationHistory getLocationHistoryById(Integer id) {
        return locationHistoryRepository.findOne(id);
    }

    @Override
    public void deleteLocationHistory(Integer id) {
        locationHistoryRepository.delete(id);
    }
}
