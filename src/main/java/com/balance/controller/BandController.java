package com.balance.controller;

import com.balance.model.*;
import com.balance.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * Created by da_20 on 31/5/2017.
 */
@RestController
public class BandController {

    private BandService bandService;
    private UserService userService;
    private CaloriesHistoryService caloriesHistoryService;
    private PulseHistoryService pulseHistoryService;
    private StepsHistoryService stepsHistoryService;
    private LocationHistoryService locationHistoryService;

    @Autowired
    public void setStepsHistoryService(StepsHistoryService stepsHistoryService) {
        this.stepsHistoryService = stepsHistoryService;
    }

    @Autowired
    public void setLocationHistoryService(LocationHistoryService locationHistoryService) {
        this.locationHistoryService = locationHistoryService;
    }

    @Autowired
    public void setPulseHistoryService(PulseHistoryService pulseHistoryService) {
        this.pulseHistoryService = pulseHistoryService;
    }

    @Autowired
    public void setCaloriesHistoryService(CaloriesHistoryService caloriesHistoryService) {
        this.caloriesHistoryService = caloriesHistoryService;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setBandService(BandService bandService) {
        this.bandService = bandService;
    }

    @RequestMapping(value = "/band", method = RequestMethod.POST)
    public List<Band> saveBand(@Valid Band band, BindingResult bindingResult, Model model) {
        Iterable<User> userList = userService.listAllUsers();
        List<Band> bandList = new ArrayList<>();

        Random rand = new Random();

        for (User u : userList) {
            band.setFecha_registro(new Date());
            Band aux = new Band();
            Integer  step = rand.nextInt(50) + 1;
            Integer bpms = rand.nextInt(39)+52;
            Integer distances = rand.nextInt(4)+1;
            Float latitude = rand.nextFloat()*7+3;
            Float longitude = rand.nextFloat()*10+5;
            Integer calories =rand.nextInt(20)+5;
            aux.asignar(band.getId(), (band.getSteps() + step) / 2, (band.getBpm() + bpms) / 2, (band.getDistance() + distances) / 2, band.getFecha_registro(), (band.getLatitude() + latitude) / 2, (band.getLongitude() + longitude) / 2, (band.getCalories() + calories) / 2, u.getId());
            caloriesHistoryService.saveCaloriesHistory(new CaloriesHistory((band.getCalories() + calories) / 2, u.getId(), band.getFecha_registro()));
            pulseHistoryService.savePulseHistory(new PulseHistory((band.getBpm() + bpms) / 2,band.getFecha_registro(), u.getId()));
            stepsHistoryService.saveStepsHistory(new StepsHistory((band.getSteps() + step) / 2, (band.getDistance() + distances) / 2, u.getId(), band.getFecha_registro()));
            locationHistoryService.saveLocationHistory(new LocationHistory((band.getLatitude() + latitude) / 2, (band.getLongitude() + longitude) / 2, u.getId(), band.getFecha_registro()));
            bandService.saveBand(aux);
            bandList.add(aux);
        }

        return bandList;
    }

    @RequestMapping(value = "/bands", method = RequestMethod.GET)
    public ResponseEntity<Iterable<Band>> getBands() {
        return new ResponseEntity(bandService.listAllBands(), HttpStatus.NOT_FOUND);
    }

}