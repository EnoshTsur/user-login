package com.enosh.userlogin.controllers;

import com.enosh.userlogin.exceptions.MissingAttributeException;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

public class ControllerUtils {

    // User
    protected static final String EMAIL = "email";
    protected static final String PASSWORD = "password";
    protected static final String FIRST_NAME = "fname";
    protected static final String LAST_NAME = "lname";

    // Address
    protected static final String CITY = "city";
    protected static final String STREET = "street";
    protected static final String NUMBER = "number";


    protected static final String USER = "user";
    protected static final String ERROR = "error";
    protected final static String REDIRECT_TO_INDEX = "redirect:/user/index";
    protected final static String REDIRECT_TO_SIGNIN = "redirect:/user/signin";

    protected final static String LOGOUT_MESSAGE = "logoutMessage";


    private static String missingAttrMessage(String name) {
        return "Missing attribute: " + name;
    }

    public static String parameterFromRequest(String name, HttpServletRequest request)
            throws MissingAttributeException {
        return Optional.ofNullable(request.getParameter(name))
                .filter(param -> !param.isEmpty())
                .orElseThrow(() -> new MissingAttributeException(
                        missingAttrMessage(name)
                ));
    }
    // input value: enosh@gmail.com

    // input vlaue.equals("")

    // Missing attribute: email
}
