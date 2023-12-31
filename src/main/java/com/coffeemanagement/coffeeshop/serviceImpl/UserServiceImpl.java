package com.coffeemanagement.coffeeshop.serviceImpl;

import com.coffeemanagement.coffeeshop.POJO.User;
import com.coffeemanagement.coffeeshop.constants.CoffeeConstants;
import com.coffeemanagement.coffeeshop.dao.UserDao;
import com.coffeemanagement.coffeeshop.service.UserService;
import com.coffeemanagement.coffeeshop.utils.CoffeeUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Objects;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserDao userDao;

    @Override
    public ResponseEntity<String> signUp(Map<String, String> requestMap) {
        log.info("UserServiceImpl {}", requestMap);

        try {
            if (this.validateSignUpMap(requestMap)) {
                User user = userDao.findByEmailId(requestMap.get("email"));

                if (Objects.isNull(user)) {
                    userDao.save(getUserFromMap(requestMap));

                    return CoffeeUtils.getResponseEntity("Successfully registred.", HttpStatus.OK);
                } else {
                    return CoffeeUtils.getResponseEntity("Email already exist.", HttpStatus.BAD_REQUEST);
                }
            } else {
                return CoffeeUtils.getResponseEntity(CoffeeConstants.INVALID_DATA, HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return CoffeeUtils.getResponseEntity(CoffeeConstants.SOMETING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);

    }

    private boolean validateSignUpMap(Map<String, String> requestMap) {
        return requestMap.containsKey("name")
                && requestMap.containsKey("contactNumber")
                && requestMap.containsKey("email")
                && requestMap.containsKey("password");
    }

    private User getUserFromMap(Map<String, String> requestMap) {
        User user = new User();

        user.setName(requestMap.get("name"));
        user.setContactNumber(requestMap.get("contactNumber"));
        user.setEmail(requestMap.get("email"));
        user.setPassword(requestMap.get("password"));
        user.setStatus("false");
        user.setRole("user");

        return user;

    }
}
