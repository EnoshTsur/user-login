package com.enosh.userlogin.validate;

import com.enosh.userlogin.model.Address;

import java.util.Objects;

public class AddressValidation extends EntityValidation {

    private static final String CITY_MESSAGE = "City can not be less than 2 characters";
    private static final String STREET_MESSAGE = "Street can not be less than 3 characters";

    public static Address validateAddress(Address address) throws IllegalStateException {
        return Validator.of(address)
                .validate(Address::getCity, isValidRange(2, 30), CITY_MESSAGE)
                .validate(Address::getStreet, isValidRange(3, 30), STREET_MESSAGE)
                .get();
    }

}
