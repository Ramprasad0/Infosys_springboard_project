package com.infosys.community.controller;

import com.infosys.community.exception.Loginexception;
import com.infosys.community.model.User;
import com.infosys.community.request.LoginRequest;
import com.infosys.community.response.AuthResponse;
import com.infosys.community.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.infosys.community.config.Provider;

@RestController
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping("/auth/signup")
    public AuthResponse signUp(@RequestBody User user) throws Loginexception {
        return userService.signUp(user);
    }

    @PostMapping("/auth/login")
    public AuthResponse login(@RequestBody LoginRequest loginRequest) throws  Loginexception {
        return userService.login(loginRequest);
    }
    @GetMapping("/auth/get-email")
    public String getEmailFromJWT(@RequestHeader("Authorization") String jwt){
        return Provider.getEmailFromJwtToken(jwt);
    }
    @GetMapping("/auth/get-user")
    public User getUser(@RequestHeader ("Authorization") String jwt){
        return userService.getUser(jwt);
    }
}
