package com.balance.controller;

import com.balance.model.User;
import com.balance.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.jws.soap.SOAPBinding;

@Controller
public class UserController {

    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
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
    public String saveUser(User user) {
        userService.saveUserEdited(user);
        return "redirect:/admin/exclusive";
    }

    //LimitedUser Controller--------------------------------------------------------

    @RequestMapping(value = "/user/profile/{id}",method = RequestMethod.GET)
    public String viewProfile(@PathVariable Integer id,Model model) {
        model.addAttribute("user",userService.getUserById(id));
        return "limited/profile";
    }

    @RequestMapping(value = "/user/edit/{id}",method = RequestMethod.GET)
    public String editProfile(@PathVariable Integer id,Model model) {
        model.addAttribute("user",userService.getUserById(id));
        return "limited/editProfile";
    }


    @RequestMapping(value = "/user",method = RequestMethod.POST)
    public String saveLimitedUser(User user) {
        userService.saveUser(user);
        return "redirect:/user/profile/" + user.getId();
    }




}
