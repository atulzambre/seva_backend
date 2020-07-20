package com.seva.api.controller;

import com.seva.api.entity.SevaUser;
import com.seva.api.service.RegisterUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RegisterUserController {
    @Autowired
    RegisterUserService registerUserService;

    @PostMapping("registerUser")
    public void registerUser(@RequestBody SevaUser sevaUser){
        registerUserService.registerUser(sevaUser);
    }
}
