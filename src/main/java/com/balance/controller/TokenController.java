package com.balance.controller;

import com.balance.Mail.SmtpMailSender;
import com.balance.model.Token;
import com.balance.model.User;
import com.balance.service.TokenService;
import com.balance.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.mail.MessagingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.security.SecureRandom;

/**
 * Created by da_20 on 4/6/2017.
 */
@Controller
public class TokenController {

    @Autowired
    private UserService userService;
    private TokenService tokenService;

    @Autowired
    private SmtpMailSender smtpMailSender;

    @Autowired
    public void setTokenService(TokenService tokenService) {
        this.tokenService = tokenService;
    }

    @RequestMapping(value="/forgot", method = RequestMethod.GET)
    public String forgotpassword(){
        return "forgot";
    }

    @RequestMapping(value="/send-mail", method = RequestMethod.GET)
    public String sendMail(HttpServletRequest request) throws MessagingException,ServletException {
        String text1= request.getParameter("email");
        if(userService.findUserByEmail(request.getParameter("email"))!=null){
            SecureRandom random = new SecureRandom();
            byte bytes[] = new byte[10];
            random.nextBytes(bytes);
            String token = bytes.toString();
            Token t=new Token(token);
            t.setUser(userService.findUserByEmail(request.getParameter("email")));
            tokenService.saveToken(t);
            User user=userService.findUserByEmail(request.getParameter("email"));
            user.setToken(t);
            userService.saveUser(user);
            smtpMailSender.send(text1, "Balance Fitness Tracker: Recover your password", "<a href='http://localhost:8080/changepassword/ "+ token + " ' > Change password </a>");
            return "redirect:/";
        }
        return "redirect:/forgot";
    }
    @RequestMapping(value="/changepassword/{token}", method = RequestMethod.GET)
    public String changepassword(@PathVariable String token) {
        Token t=tokenService.findByToken("[B@"+token.substring(4));
        if(t!=null && t.getActive()!=false && t.getUser()!=null){
            return "changepassword";
        }
        return "redirect:/";
    }

    @RequestMapping(value="/changepasswordyes", method = RequestMethod.GET)
    public String changepasswordyes(String email,String password) {
        User userExists = userService.findUserByEmail(email);
        if (userExists != null) {
            System.out.println("Llego aqui");
            if(!userExists.getToken().equals(null)){
                BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
                userExists.setPassword(bCryptPasswordEncoder.encode(password));
                tokenService.getTokenById(userExists.getToken().getId()).setActive(false);
                Integer id=tokenService.getTokenById(userExists.getToken().getId()).getId();
                tokenService.getTokenById(userExists.getToken().getId()).setUser(null);
                userExists.setToken(null);
                tokenService.deleteToken(id);
                userExists.setToken(null);
                userService.saveUser(userExists);
                return "redirect:/";
            }
        }
        return "redirect:changepassword";
    }
}