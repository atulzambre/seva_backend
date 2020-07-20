package com.seva.api.controller;

import com.seva.api.entity.SevaRole;
import com.seva.api.service.SevaRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PingController {
    @Autowired
    SevaRoleService sevaRoleService;

    @GetMapping("healthCheck")
    public List<SevaRole> getHealth(){
        return sevaRoleService.getAllRoles();
    }
}
