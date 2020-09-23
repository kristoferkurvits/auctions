package com.Kurvits.bacchusback.User;


import com.Kurvits.bacchusback.User.service.UserServiceImpl;
import org.json.simple.parser.ParseException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.io.IOException;


@RestController
public class UserController {

    private final UserServiceImpl userService;

    public UserController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @PostMapping("/users")
    ResponseEntity<User> createUser(@Valid @RequestBody User newUser) throws IOException, ParseException {
        newUser.checkDateValidity(newUser.getProductId());
        if (newUser.isValidBid()){
	    User savedUser = userService.saveUser(newUser);
            return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
        }

        throw new ForbiddenException();
    }
}
