package com.seva.api.controller;

import com.seva.api.entity.SevaUser;
import com.seva.api.service.LoginUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
public class LoginUserController {

    @Autowired
    LoginUserService loginUserService;

    @PostMapping("loginUser")
    public SevaUser loginUser(@RequestBody SevaUser sevaUser){
        return loginUserService.loginUserService(sevaUser);
    }
}
