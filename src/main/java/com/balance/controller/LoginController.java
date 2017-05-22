package com.balance.controller;

import javax.mail.MessagingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import com.balance.SmtpMailSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.balance.model.User;
import com.balance.service.UserService;
import java.util.Set;

@Controller
public class LoginController {

	@Autowired
	private UserService userService;

	@Autowired
	private SmtpMailSender smtpMailSender;

	@RequestMapping(value={"/", "/login"}, method = RequestMethod.GET)
	public String login(){
		return "login";
	}

	@RequestMapping(value="/registration", method = RequestMethod.GET)
	public String registration(Model model){
		model.addAttribute("user", new User());
		return "registration";
	}

	@RequestMapping(value = "/registration", method = RequestMethod.POST)
	public String createNewUser(@Valid User user, BindingResult bindingResult, Model model) {
		User userExists = userService.findUserByEmail(user.getEmail());
		if (userExists != null) {
			bindingResult.rejectValue("email", "error.user", "There is already a user registered with the email provided");
		}
		if (!bindingResult.hasErrors()) {
			userService.saveUser(user);
			model.addAttribute("successMessage", "El usuario se registro correctamente");
			model.addAttribute("user", new User());
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
		return "admin/home";
	}

	@RequestMapping(value="/user/home", method = RequestMethod.GET)
	public String homeExclusive(Model model){
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
		model.addAttribute("userName", "Welcome " + user.getName() + " " + user.getLastName() + " (" + user.getEmail() + ")");
		model.addAttribute("userMessage","Content Available Only for Users with Limited Role");
		return "admin/exclusive";
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

	@RequestMapping(value="/forgot", method = RequestMethod.GET)
	public String forgotpassword(){
		return "forgot";
	}

	@RequestMapping(value="/send-mail", method = RequestMethod.GET)
	public String sendMail(HttpServletRequest request) throws MessagingException,ServletException {
		String text1= request.getParameter("email");
		smtpMailSender.send(text1, "Balance Fitness Tracker: Recover your password", "recover password: <a href='http://localhost:8080/changepassword'>Recover password</a>");
		return "redirect:/";
	}
	@RequestMapping(value="/changepassword", method = RequestMethod.GET)
	public String changepassword() {
		return "changepassword";
	}
	@RequestMapping(value="/changepasswordyes", method = RequestMethod.GET)
	public String changepassword1(String email,String password) {
		User userExists = userService.findUserByEmail(email);
		if (userExists != null) {
			userExists.setPassword(password);
			userService.saveUser(userExists);
		}
		return "redirect:/";
	}
}