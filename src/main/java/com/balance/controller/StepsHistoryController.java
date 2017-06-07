package com.balance.controller;

import com.balance.model.Band;
import com.balance.model.StepsHistory;
import com.balance.service.StepsHistoryService;
import com.balance.service.UserService;
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
public class StepsHistoryController {
    StepsHistoryService stepsHistoryService;
    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

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
        Date fechaactual = new Date();
        while(iterator.hasNext()){
                StepsHistory aux=iterator.next();
                if(aux.getUser().equals(id)  &&
                        fechaactual.getDay()==aux.getDate().getDay() &&
                        fechaactual.getMonth()==aux.getDate().getMonth() &&
                        fechaactual.getYear()==aux.getDate().getYear()){
                steps += aux.getSteps();
            }
        };

        StepsHistory stepsHistory = new StepsHistory();
        stepsHistory.setSteps(steps);
        stepsHistory.setId(67620L);
        return stepsHistory;
    }

    @RequestMapping(value = "/getDistance/{id}", method = RequestMethod.GET)
    public StepsHistory getDistance(@PathVariable Integer id) {
        long distance = 0;

        Iterator<StepsHistory> iterator = stepsHistoryService.listAllStepsHistory().iterator();
        List<StepsHistory> myList=new ArrayList<>();
        Date fechaactual = new Date();
        while(iterator.hasNext()){
            myList.add(iterator.next());
        }

        for(StepsHistory sh:myList){
            if(sh.getUser().equals(id) &&
                    fechaactual.getDay()==sh.getDate().getDay() &&
                    fechaactual.getMonth()==sh.getDate().getMonth() &&
                    fechaactual.getYear()==sh.getDate().getYear()){
                distance+=sh.getDistance();
            }
        }

        StepsHistory stepsHistorynew = new StepsHistory();
        stepsHistorynew.setDistance(distance);
        stepsHistorynew.setId(67620L);
        return stepsHistorynew;
    }

}