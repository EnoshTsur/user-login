package com.enosh.userlogin.controllers;

import com.enosh.userlogin.dao.UserDao;
import com.enosh.userlogin.exceptions.DoesntExistsException;
import com.enosh.userlogin.exceptions.MissingAttributeException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

import static com.enosh.userlogin.controllers.ControllerUtils.*;

@Controller
@RequestMapping("/user")
public class UserController {

    private final UserDao userDao;

    private final String REDIRECT_TO_INDEX = "redirect:/user/index";

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
        try {
            String email = parameterFromRequest("email", request);
            String password = parameterFromRequest("password", request);
            if (userDao.login(email, password)) {
                request.getSession().setAttribute(
                        USER,
                        userDao.findByEmailAndPassword(email, password)
                );

                return REDIRECT_TO_INDEX;
            }
            model.addAttribute(ERROR, "Invalid email or password");
            return signin();

        } catch (MissingAttributeException | DoesntExistsException e) {
            model.addAttribute(ERROR, e.getMessage());
            return signin();
        }
    }
}
