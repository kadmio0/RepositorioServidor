package com.balance.controller;

import com.balance.model.User;
import com.balance.service.ModelService;
import com.balance.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.w3c.dom.stylesheets.LinkStyle;

import javax.jws.soap.SOAPBinding;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Iterator;

@Controller
public class UserController {

    private UserService userService;
    private ModelService modelService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setModelService(ModelService modelService){
        this.modelService=modelService;
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

    //LimitedUser Controller--------------------------------------------------------

    @RequestMapping(value = "/user/profile",method = RequestMethod.GET)
    public String viewProfile(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        model.addAttribute("user", user);
        return "limited/profile";
    }

    @RequestMapping(value = "/user/edit/{id}",method = RequestMethod.GET)
    public String editProfile(@PathVariable Integer id,Model model) {
        model.addAttribute("user",userService.getUserById(id));
        model.addAttribute("models",modelService.listAllModels());
        return "limited/editProfile";
    }

    @RequestMapping(value = "/user",method = RequestMethod.POST)
    public String saveLimitedUser(@Valid User user) {
        userService.saveUserEdited(user);
        return "redirect:/user/profile/";
    }


}
