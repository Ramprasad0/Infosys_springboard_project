package com.infosys.community.service;


import com.infosys.community.exception.Loginexception;
import com.infosys.community.request.LoginRequest;
import com.infosys.community.response.AuthResponse;
import com.infosys.community.model.User;

public interface UserService {
     public AuthResponse signUp(User user) throws Loginexception;
     public AuthResponse login(LoginRequest loginData) throws Loginexception, Loginexception;
     public User getUser(String jwt);

}