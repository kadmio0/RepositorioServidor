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
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

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

    @RequestMapping(value = "/calories", method = RequestMethod.POST)
    public CaloriesHistory saveCalories(@Valid CaloriesHistory caloriesHistory, BindingResult bindingResult, Model model) {
        caloriesHistory.setDate(new Date());

        caloriesHistoryService.saveCaloriesHistory(caloriesHistory);
        return caloriesHistory;
    }

    @RequestMapping(value = "/calories", method = RequestMethod.GET)
    public ResponseEntity<Iterable<CaloriesHistory>> getCaloriess() {
        return new ResponseEntity(caloriesHistoryService.listAllCaloriesHistorys(), HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "/getCalories/{id}", method = RequestMethod.GET)
    public CaloriesHistory getCalories(@PathVariable Integer id) {
        double calories = 0;

        Iterator<CaloriesHistory> iterator = caloriesHistoryService.listAllCaloriesHistorys().iterator();

        while(iterator.hasNext()){
            if(iterator.next().getUser().equals(id)) {
                CaloriesHistory aux = iterator.next();
                calories += aux.getCalories();
            }
        }

        CaloriesHistory caloriesnew = new CaloriesHistory();
        caloriesnew.setCalories(calories);
        caloriesnew.setId(67626L);
        return caloriesnew;
    }



}
