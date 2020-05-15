package com.enosh.userlogin.validate;

import java.util.function.Predicate;

public class EntityValidation {

    protected static Predicate<String> isValidRange(int min, int max) {
        return str -> str.length() >= min && str.length() <= max;
    }
}
