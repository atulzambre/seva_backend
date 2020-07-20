package com.seva.api.serviceimpl;

import com.seva.api.entity.SevaUser;
import com.seva.api.repository.SevaRoleRepository;
import com.seva.api.repository.SevaUserRepository;
import com.seva.api.service.RegisterUserService;
import com.seva.api.util.EncodeDecodeBase64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;

@Service
public class RegisterUserServiceImpl implements RegisterUserService {
    @Autowired
    SevaUserRepository sevaUserRepository;
    @Autowired
    EncodeDecodeBase64 encodeDecodeBase64;
    @Override
    public void registerUser(SevaUser sevaUser) {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        sevaUser.setSuCreatedDate(timestamp);
        sevaUser.setSuPassword(encodeDecodeBase64.encode(sevaUser.getSuPassword()));
        sevaUserRepository.save(sevaUser);
    }
}
