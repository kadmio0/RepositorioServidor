package com.balance.controller;

import com.balance.model.*;
import com.balance.repository.TerminalRepository;
import com.balance.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Iterator;

@Controller
public class UserController {

    private UserService userService;
    private TerminalService terminalService;
    private BandModelService bandModelService;
    private BandService bandService;
    private CaloriesHistoryService caloriesHistoryService;
    private PulseHistoryService pulseHistoryService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setTerminalService(TerminalService terminalService){
        this.terminalService=terminalService;
    }

    @Autowired
    public void setBandModelService(BandModelService bandModelService){
        this.bandModelService=bandModelService;
    }

    @Autowired
    public void setBandService(BandService bandService){
        this.bandService=bandService;
    }

    @Autowired
    public void setCaloriesHistoryService(CaloriesHistoryService caloriesHistoryService){
        this.caloriesHistoryService=caloriesHistoryService;
    }

    @Autowired
    public void setPulseHistoryService(PulseHistoryService pulseHistoryService) {
        this.pulseHistoryService = pulseHistoryService;
    }

    @RequestMapping(value = "/admin/user/{id}", method = RequestMethod.GET)
    public String showUser(@PathVariable Integer id, Model model) {
        model.addAttribute("user", userService.getUserById(id));
        return "admin/user";
    }

    @RequestMapping(value = "/admin/user/delete/{id}", method = RequestMethod.GET)
    public String deleteUser(@PathVariable Integer id) {
        userService.deleteUser(id);
        return "redirect:/admin/home";
    }

    @RequestMapping(value = "/admin/user/edit/{id}",method = RequestMethod.GET)
    public String editUser(@PathVariable Integer id,Model model) {
        model.addAttribute("user",userService.getUserById(id));
        return "admin/userForm";
    }

    @RequestMapping(value = "/admin/user", method = RequestMethod.POST)
    public String saveUser(@Valid User user) {
        userService.saveUserEdited(user);
        return "redirect:/admin/home";
    }

    @RequestMapping(value = "/admin/user/history/{id}", method = RequestMethod.GET)
    public String viewHistory(@PathVariable Integer id, Model model) {
        User user = userService.getUserById(id);

        Iterator<PulseHistory> iterator = pulseHistoryService.listAllPulseHistory().iterator();
        ArrayList<PulseHistory> resp = new ArrayList<PulseHistory>();
        while(iterator.hasNext()){
            PulseHistory aux = iterator.next();
            if(aux.getUser().equals(user.getId())) {
                resp.add(aux);
            }
        }
        model.addAttribute("user",user);
        model.addAttribute("pulses", resp);

        Iterator<CaloriesHistory> iterator2 = caloriesHistoryService.listAllCaloriesHistorys().iterator();
        ArrayList<CaloriesHistory> resp2=new ArrayList<CaloriesHistory>();
        while(iterator2.hasNext()){
            CaloriesHistory aux = iterator2.next();
            if(aux.getUser().equals(user.getId())) {
                resp2.add(aux);
            }
        }

        model.addAttribute("calories",resp2);

        return "admin/history";
    }

    //LimitedUser Controller--------------------------------------------------------

    @RequestMapping(value = "/user/profile",method = RequestMethod.GET)
    public String viewProfile(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        Terminal ant=terminalService.getTerminalById(userService.getUserById(user.getId()).getTerminal().getSerial());
        ant.setActive(true);
        terminalService.saveTerminal(ant);
        model.addAttribute("user", user);
        return "limited/profile";
    }

    @RequestMapping(value = "/user/edit",method = RequestMethod.GET)
    public String editProfile(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        model.addAttribute("user",userService.getUserById(user.getId()));

        Terminal ant=terminalService.getTerminalById(userService.getUserById(user.getId()).getTerminal().getSerial());
        ant.setActive(false);

        terminalService.saveTerminal(ant);
        model.addAttribute("bands",bandModelService.listAllBandModels());

        return "limited/editProfile";
    }

    @RequestMapping(value = "/user",method = RequestMethod.POST)
    public String saveLimitedUser(@Valid User user, BindingResult bindingResult) {
        if(user.getTerminal()==null) {
            return "redirect:/user/edit";
        }
        Terminal newTerminal = terminalService.getTerminalById(user.getTerminal().getSerial());

        if(!newTerminal.getBandModel().getName().equals(user.getBand()) || newTerminal.isActive()){
            return "redirect:/user/edit";
        }

        if(bindingResult.hasErrors()) {
            return "limited/editProfile";
        }

        newTerminal.setActive(true);
        terminalService.saveTerminal(newTerminal);
        user.setTerminal(newTerminal);

        userService.saveUserEdited(user);
        return "redirect:/user/profile/";
    }

    @RequestMapping(value = "/user/CaloriesHistory", method = RequestMethod.GET)
    public String getCaloriesHistory(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());

        Iterator<CaloriesHistory> iterator = caloriesHistoryService.listAllCaloriesHistorys().iterator();
        ArrayList<CaloriesHistory> resp=new ArrayList<CaloriesHistory>();
        while(iterator.hasNext()){
            CaloriesHistory aux = iterator.next();
            if(aux.getUser().equals(user.getId())) {
                resp.add(aux);
            }
        }
        model.addAttribute("user",user);
        model.addAttribute("calories",resp);
        return "limited/caloriesHistory";
    }

    @RequestMapping(value = "/user/PulseHistory", method = RequestMethod.GET)
    public String getPulsesHistory(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        Iterator<PulseHistory> iterator = pulseHistoryService.listAllPulseHistory().iterator();
        ArrayList<PulseHistory> resp = new ArrayList<PulseHistory>();
        while(iterator.hasNext()){
            PulseHistory aux = iterator.next();
            if(aux.getUser().equals(user.getId())) {
               resp.add(aux);
            }
        }
        model.addAttribute("user",user);
        model.addAttribute("pulses", resp);
        return "limited/pulseHistory";
    }
}
