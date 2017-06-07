package com.balance.controller;

import com.balance.model.LocationHistory;
import com.balance.service.LocationHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * Created by da_20 on 6/6/2017.
 */
@RestController
public class LocationHistoryController {
    private LocationHistoryService locationHistoryService;

    @Autowired
    public void setLocationHistoryService(LocationHistoryService locationHistoryService) {
        this.locationHistoryService = locationHistoryService;
    }

    @RequestMapping(value = "/locations", method = RequestMethod.GET)
    public ResponseEntity<Iterable<LocationHistory>> getLocationHistories() {
        return new ResponseEntity(locationHistoryService.listAllLocationHistory(), HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "/getLocations/{id}", method = RequestMethod.GET)
    public LocationHistory getLocations(@PathVariable Integer id) {
        float latitude = 0;
        float longitude = 0;
        Iterator<LocationHistory> iterator = locationHistoryService.listAllLocationHistory().iterator();
        List<LocationHistory> myList=new ArrayList<>();
        Date fechaactual = new Date();
        while(iterator.hasNext()){
            myList.add(iterator.next());
        }

        for(LocationHistory lh:myList){
            if(lh.getUser().equals(id) &&
                    fechaactual.getDay()==lh.getDate().getDay() &&
                    fechaactual.getMonth()==lh.getDate().getMonth() &&
                    fechaactual.getYear()==lh.getDate().getYear()){
                latitude+=lh.getLatitude();
                longitude+=lh.getLongitude();
            }
        }

        LocationHistory locationHistory = new LocationHistory();
        locationHistory.setLatitude(latitude);
        locationHistory.setLongitude(longitude);
        locationHistory.setId(67620L);
        return locationHistory;

    }
}
