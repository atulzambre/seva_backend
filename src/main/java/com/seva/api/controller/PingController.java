package com.seva.api.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
@CrossOrigin
@RestController
public class PingController {

    @GetMapping("healthCheck")
    public String getHealth(){
        return "Works Fine!!";
    }
}
