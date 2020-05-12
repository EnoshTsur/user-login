package com.enosh.userlogin.controllers;

import com.enosh.userlogin.dao.UserDao;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/user")
public class UserController {

    private final UserDao userDao;

    public UserController(UserDao userDao) {
        this.userDao = userDao;
    }

    @GetMapping("/signin")
    public String signin(){
        return "login/signin";
    }

    @GetMapping("/signup")
    public String signup(){
        return "login/signup";
    }

    @PostMapping("/signinHandler")
    public String signinHandler(HttpServletRequest request, Model model) {

    }
}
