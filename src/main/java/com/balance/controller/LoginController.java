package com.balance.controller;

import javax.mail.MessagingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import com.balance.Mail.SmtpMailSender;
import com.balance.model.*;
import com.balance.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;

@Controller
public class LoginController {

	@Autowired
	private UserService userService;
	private TerminalService terminalService;
	private BandModelService bandModelService;
	private CaloriesHistoryService caloriesHistoryService;
	private PulseHistoryService pulseHistoryService;
	private StepsHistoryService stepsHistoryService;

	@Autowired
    public void setStepsHistoryService(StepsHistoryService stepsHistoryService) {
        this.stepsHistoryService = stepsHistoryService;
    }

	@Autowired
	public void setCaloriesHistoryService(CaloriesHistoryService caloriesHistoryService) {
		this.caloriesHistoryService = caloriesHistoryService;
	}
	@Autowired
	public void setPulseHistoryService(PulseHistoryService pulseHistoryService) {
		this.pulseHistoryService = pulseHistoryService;
	}

	@Autowired
	public void setTerminalService(TerminalService terminalService){
		this.terminalService=terminalService;
	}

	@Autowired
	public void setBandModelService(BandModelService bandModelService){
		this.bandModelService=bandModelService;
	}

	@RequestMapping(value={"/", "/login"}, method = RequestMethod.GET)
	public String login(){
		return "login";
	}

	@RequestMapping(value="/registration", method = RequestMethod.GET)
	public String registration(Model model){
		model.addAttribute("user", new User());
		model.addAttribute("bands",bandModelService.listAllBandModels());
		return "registration";
	}

	@RequestMapping(value = "/registration", method = RequestMethod.POST)
	public String createNewUser(@Valid User user, BindingResult bindingResult, Model model) {
		User userExists = userService.findUserByEmail(user.getEmail());
		if (userExists != null) {
			bindingResult.rejectValue("email", "error.user", "There is already a user registered with the email provided");
		}
		if(user.getTerminal()==null){
			return "redirect:/registration";
		}
		if (!bindingResult.hasErrors() && !terminalService.getTerminalById(user.getTerminal().getSerial()).isActive()) {
			userService.saveUser(user);
			model.addAttribute("successMessage", "El usuario se registro correctamente");
			model.addAttribute("user", new User());
		}else{
			return "redirect:/registration";
		}

		return "registration";
	}

	@RequestMapping(value="/admin/home", method = RequestMethod.GET)
	public String home(Model model){
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
		if (user == null) {
			return "redirect:/";
		}
		model.addAttribute("userName", "Welcome " + user.getName() + " " + user.getLastName() + " (" + user.getEmail() + ")");
		model.addAttribute("adminMessage","Content Available Only for Users with Admin Role");
		model.addAttribute("userList", userService.listAllUsers());
		model.addAttribute("user", user);
		return "admin/home";
	}

    @RequestMapping(value="/user/home", method = RequestMethod.GET)
    public String homeExclusive(Model model){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        model.addAttribute("userName", "Welcome " + user.getName() + " " + user.getLastName() + " (" + user.getEmail() + ")");
        model.addAttribute("userMessage","Content Available Only for Users with Limited Role");
        model.addAttribute("user", user);
        int steps = 0;
        double calories=0;
        long distance=0;
        int bpm= 0;

        Iterator<CaloriesHistory> iterator2 = caloriesHistoryService.listAllCaloriesHistorys().iterator();
        Iterator<StepsHistory> iterator3 = stepsHistoryService.listAllStepsHistory().iterator();
        Iterator<PulseHistory> iterator4=pulseHistoryService.listAllPulseHistory().iterator();
        StepsHistory aux = new StepsHistory();
        CaloriesHistory caux= new CaloriesHistory();
		PulseHistory caux2 = new PulseHistory();

        while(iterator3.hasNext()){
            aux = iterator3.next();
            steps += aux.getSteps();
            distance+=aux.getDistance();
        };


        while(iterator2.hasNext()){
            caux = iterator2.next();
            calories += caux.getCalories();

        }

		while(iterator4.hasNext()){
			caux2 = iterator4.next();
			bpm += caux2.getBpm();

		}

        model.addAttribute("countSteps",steps);
        model.addAttribute("countCalories",calories);
        model.addAttribute("countDistance",distance);
		model.addAttribute("countPulse",bpm);
        model.addAttribute("id",user.getId());
        return "user/home";
    }

	@RequestMapping(value="/default", method = RequestMethod.GET)
	public String defaultAfterLogin()
	{
		Set<String> roles = AuthorityUtils.authorityListToSet(SecurityContextHolder.getContext().getAuthentication().getAuthorities());
		if (roles.contains("ADMIN")) {
			return "redirect:/admin/home";
		}
		return "redirect:/user/home";
	}

	@RequestMapping(value = "/access-denied", method = RequestMethod.GET)
	public String accesoDenegado() {
		return "access-denied"; //Solo devuelve un mensaje
	}

	@RequestMapping(value = "/admin/users", method = RequestMethod.GET)
	public String listUsers(Model model) {
		model.addAttribute("Users",userService.listAllUsers());
		return "users";
	}

}