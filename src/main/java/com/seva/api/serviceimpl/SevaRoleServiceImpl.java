package com.seva.api.serviceimpl;

import com.seva.api.entity.SevaRole;
import com.seva.api.repository.SevaRoleRepository;
import com.seva.api.service.SevaRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SevaRoleServiceImpl implements SevaRoleService {
    @Autowired
    SevaRoleRepository sevaRoleRepository;
    @Override
    public List<SevaRole> getAllRoles() {
        return sevaRoleRepository.findAll();
    }
}
