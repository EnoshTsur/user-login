package com.enosh.userlogin.validate;

import com.enosh.userlogin.model.User;

import javax.swing.text.html.parser.Entity;
import java.util.function.BiFunction;
import java.util.function.Predicate;


public class UserValidation extends EntityValidation {

    private static final String FIRST_NAME_MESSAGE = "First Name can not be less than 2 characters";
    private static final String LAST_NAME_MESSAGE = "Last Name can not be less than 2 characters";
    private static final String EMAIL_MESSAGE = "Email can not be less than 2 characters";
    private static final String PASSWORD_MESSAGE = "Password can not be less than 2 characters";


    public static User validateUser(User user) throws IllegalStateException {
        return Validator.of(user)
                .validate(User::getFirstName, isValidRange(2, 30), FIRST_NAME_MESSAGE)
                .validate(User::getLastName, isValidRange(2, 30), LAST_NAME_MESSAGE)
                .validate(User::getEmail, isValidRange(5, 30), EMAIL_MESSAGE)
                .validate(User::getPassword, isValidRange(5, 255), PASSWORD_MESSAGE)
                .get();
    }
}
