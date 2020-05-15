package com.enosh.userlogin.controllers;

import com.enosh.userlogin.dao.UserDao;
import com.enosh.userlogin.exceptions.DoesntExistsException;
import com.enosh.userlogin.exceptions.MissingAttributeException;
import com.enosh.userlogin.model.Address;
import com.enosh.userlogin.model.User;
import com.enosh.userlogin.validate.AddressValidation;
import com.enosh.userlogin.validate.UserValidation;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.enosh.userlogin.controllers.ControllerUtils.*;
import static com.enosh.userlogin.validate.AddressValidation.*;
import static com.enosh.userlogin.validate.UserValidation.*;

// http://enosh.com:8080/user

@Controller
@RequestMapping("/user")
public class UserController {

    private final UserDao userDao;

    public UserController(UserDao userDao) {
        this.userDao = userDao;
    }


    // http://enosh.com:8080/user/signin
    @GetMapping("/signin")
    public String signin() {
        return "login/signin";
    }

    @GetMapping("/signup")
    public String signup() {
        return "login/signup";
    }

    @PostMapping("/signupHandler")
    public String signupHandler(HttpServletRequest request, Model model) {
        try {

            // User
            String firstName = parameterFromRequest(FIRST_NAME, request);
            String lastName = parameterFromRequest(LAST_NAME, request);
            String email = parameterFromRequest(EMAIL, request);
            String password = parameterFromRequest(PASSWORD, request);

            User user = validateUser(new User(firstName, lastName, email, password));

            //Address
            String city = parameterFromRequest(CITY, request);
            String street = parameterFromRequest(STREET, request);
            int number = Integer.parseInt(parameterFromRequest(NUMBER, request).trim());

            Address address = validateAddress(new Address(city, street, number));

            User afterSave = userDao.save(user, address);
            request.getSession().setAttribute(USER, afterSave);
            return REDIRECT_TO_INDEX;

        } catch (MissingAttributeException | NumberFormatException e) {
            model.addAttribute(ERROR, e.getMessage());
            return signup();

        } catch (IllegalStateException e) {
            model.addAttribute(
                    ERROR,
                    Stream.of(e.getSuppressed())
                            .map(Throwable::getMessage)
                            .collect(Collectors.joining(", "))
            );
            return signup();
        }
    }

    // http://enosh.com:8080/use/signin
    @PostMapping("/signinHandler")
    public String signinHandler(HttpServletRequest request, Model model) {
        try {

            String email = parameterFromRequest(EMAIL, request);
            String password = parameterFromRequest(PASSWORD, request);

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

    @PostMapping("/logout")
    public String logout(HttpServletRequest request, Model model) {
        String bye = "Bye Bye ";
        Optional.ofNullable(
                (User) request.getSession().getAttribute(USER))
                .ifPresent(user -> {
                    request.getSession().removeAttribute(USER);
                    model.addAttribute(
                            LOGOUT_MESSAGE,
                            bye + user.getFirstName() + "-" + user.getLastName()
                    );
                });
        return "logout";
    }

    @GetMapping({"", "/", "/index", "/index.html"})
    public String index(HttpServletRequest request, Model model) {
        if (request.getSession().getAttribute(USER) == null) {
            return REDIRECT_TO_SIGNIN;
        }
        model.addAttribute(USER, request.getSession().getAttribute(USER));
        return "index";
    }
}
