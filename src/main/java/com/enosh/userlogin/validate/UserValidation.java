package com.enosh.userlogin.validate;

import com.enosh.userlogin.model.User;

public class UserValidation {

    private static final String FIRST_NAME_MESSAGE = "First Name can not be less than 2 characters";
    private static final String LAST_NAME_MESSAGE = "Last Name can not be less than 2 characters";
    private static final String EMAIL_MESSAGE = "Email can not be less than 2 characters";
    private static final String PASSWORD_MESSAGE = "Password can not be less than 2 characters";

    public static User validateUser(User user) throws IllegalStateException {
        return Validator.of(user)
                .validate(User::getFirstName, fname -> fname.length() >= 2, FIRST_NAME_MESSAGE)
                .validate(User::getLastName, lname -> lname.length() >= 2, LAST_NAME_MESSAGE)
                .validate(User::getEmail, email -> email.length() >= 5, EMAIL_MESSAGE)
                .validate(User::getPassword, pwd -> pwd.length() >= 5, PASSWORD_MESSAGE)
                .get();
    }
}
