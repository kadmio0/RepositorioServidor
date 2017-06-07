package com.balance.controller;

import com.balance.model.Band;
import com.balance.model.CaloriesHistory;
import com.balance.model.User;
import com.balance.service.CaloriesHistoryService;
import com.balance.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * Created by KEVIN on 05/06/2017.
 */
@RestController
public class CaloriesHistoryController {
    private CaloriesHistoryService caloriesHistoryService;
    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setCaloriesHistoryService(CaloriesHistoryService caloriesHistoryService){
        this.caloriesHistoryService=caloriesHistoryService;
    }

    @RequestMapping(value = "/calories", method = RequestMethod.GET)
    public ResponseEntity<Iterable<CaloriesHistory>> getCaloriess() {
        return new ResponseEntity(caloriesHistoryService.listAllCaloriesHistorys(), HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "/getCalories/{id}", method = RequestMethod.GET)
    public CaloriesHistory getCalories(@PathVariable Integer id) {
        Integer calories = 0;
        Iterator<CaloriesHistory> iterator = caloriesHistoryService.listAllCaloriesHistorys().iterator();
        List<CaloriesHistory> myList=new ArrayList<>();
        Date fechaactual = new Date();
        while(iterator.hasNext()){
            myList.add(iterator.next());
        }

        for(CaloriesHistory ch:myList){
            if(ch.getUser().equals(id) &&
                    fechaactual.getDay()==ch.getDate().getDay() &&
                    fechaactual.getMonth()==ch.getDate().getMonth() &&
                    fechaactual.getYear()==ch.getDate().getYear()){
                calories+=ch.getCalories();
            }
        }

        CaloriesHistory caloriesnew = new CaloriesHistory();
        caloriesnew.setCalories(calories);
        caloriesnew.setId(67620L);
        return caloriesnew;
    }



}
