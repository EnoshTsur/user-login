package com.enosh.userlogin.controllers;

import com.enosh.userlogin.exceptions.MissingAttributeException;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

public class ControllerUtils {

    public static final String USER = "user";
    public static final String ERROR = "error";

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
