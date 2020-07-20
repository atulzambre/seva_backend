package com.seva.api.controller;

import com.seva.api.entity.SevaUser;
import com.seva.api.service.RegisterUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
public class RegisterUserController {
    @Autowired
    RegisterUserService registerUserService;

    @PostMapping("registerUser")
    public SevaUser registerUser(@RequestBody SevaUser sevaUser) {
        SevaUser sevaUserSaved = registerUserService.registerUser(sevaUser);
        return sevaUserSaved;
    }

    @GetMapping("verifyAccount/token/{jwtToken}")
    public SevaUser verifyAccount(@PathVariable("jwtToken") String JWTToken) {
        registerUserService.verifyAccount(JWTToken);
        return new SevaUser();
    }
}
