package com.seva.api.serviceimpl;

import com.seva.api.entity.SevaUser;
import com.seva.api.repository.SevaUserRepository;
import com.seva.api.service.LoginUserService;
import com.seva.api.util.EncodeDecodeBase64Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class LoginUserServiceImpl implements LoginUserService {
    @Autowired
    SevaUserRepository sevaUserRepository;
    @Autowired
    EncodeDecodeBase64Util encodeDecodeBase64Util;

    @Override
    public SevaUser loginUserService(SevaUser sevaUser) {
        SevaUser loggedInUser=sevaUserRepository.findByEmailAndPassword(sevaUser.getSuEmail(),encodeDecodeBase64Util.encode(sevaUser.getSuPassword()));
        if(Objects.isNull(loggedInUser)){
            return null;
        }
        return loggedInUser;
    }
}
