package com.coffeemanagement.coffeeshop.restImpl;

import com.coffeemanagement.coffeeshop.constants.CoffeeConstants;
import com.coffeemanagement.coffeeshop.rest.UserRest;
import com.coffeemanagement.coffeeshop.service.UserService;
import com.coffeemanagement.coffeeshop.utils.CoffeeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class UserRestImpl implements UserRest {

    @Autowired
    UserService userService;

    @Override
    public ResponseEntity<String> signUp(Map<String, String> requestMap) {
        try {

            return userService.signUp(requestMap);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return CoffeeUtils.getResponseEntity(CoffeeConstants.SOMETING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
