package com.balance.controller;

import com.balance.model.Band;
import com.balance.model.StepsHistory;
import com.balance.service.StepsHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Iterator;

/**
 * Created by da_20 on 6/6/2017.
 */
public class StepsHistoryController {
    StepsHistoryService stepsHistoryService;

    @Autowired
    public void setStepsHistoryService(StepsHistoryService stepsHistoryService) {
        this.stepsHistoryService = stepsHistoryService;
    }

    @RequestMapping(value = "/steps", method = RequestMethod.GET)
    public ResponseEntity<Iterable<StepsHistory>> getStepsHistories() {
        return new ResponseEntity(stepsHistoryService.listAllStepsHistory(), HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "/getSteps/{id}", method = RequestMethod.GET)
    public StepsHistory getSteps(@PathVariable Integer id) {
        Integer steps = 0;

        Iterator<StepsHistory> iterator = stepsHistoryService.listAllStepsHistory().iterator();

        while(iterator.hasNext()){
            if(iterator.next().getUser().equals(id)) {
                steps += iterator.next().getSteps();
            }

        };

        StepsHistory stepsHistory = new StepsHistory();
        stepsHistory.setSteps(steps);
        stepsHistory.setId(67626L);
        return stepsHistory;
    }

    @RequestMapping(value = "/getDistance/{id}", method = RequestMethod.GET)
    public StepsHistory getDistance(@PathVariable Integer id) {
        long distance = 0;

        Iterator<StepsHistory> iterator = stepsHistoryService.listAllStepsHistory().iterator();

        while(iterator.hasNext()){
            if(iterator.next().getUser().equals(id)) {
                distance += iterator.next().getDistance();
            }

        };

        StepsHistory stepsHistory = new StepsHistory();
        stepsHistory.setDistance(distance);
        stepsHistory.setId(67626L);
        return stepsHistory;
    }

}