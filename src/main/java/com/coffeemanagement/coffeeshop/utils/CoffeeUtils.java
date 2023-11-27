package com.coffeemanagement.coffeeshop.utils;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class CoffeeUtils {
    private CoffeeUtils() {
    }

    public static ResponseEntity<String> getResponseEntity(String responseMessage, HttpStatus httpStatus) {
        return new ResponseEntity<String>("{\"message\":\"" + responseMessage + "\"}", httpStatus);
    }
}
