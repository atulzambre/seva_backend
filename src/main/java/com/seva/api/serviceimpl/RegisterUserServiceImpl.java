package com.seva.api.serviceimpl;

import com.seva.api.entity.SevaRole;
import com.seva.api.entity.SevaUser;
import com.seva.api.entity.SevaUserRole;
import com.seva.api.repository.SevaUserRepository;
import com.seva.api.repository.SevaUserRoleRepository;
import com.seva.api.service.RegisterUserService;
import com.seva.api.util.EncodeDecodeBase64Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.Timestamp;

@Service
public class RegisterUserServiceImpl implements RegisterUserService {
    @Autowired
    SevaUserRepository sevaUserRepository;
    @Autowired
    SevaUserRoleRepository sevaUserRoleRepository;
    @Autowired
    EncodeDecodeBase64Util encodeDecodeBase64;


    @Override
    @Transactional
    public SevaUser registerUser(SevaUser sevaUser) {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        sevaUser.setSuCreatedDate(timestamp);
        sevaUser.setSuPassword(encodeDecodeBase64.encode(sevaUser.getSuPassword()));
        SevaUser sevaUserSaved=sevaUserRepository.save(sevaUser);
        SevaUserRole sevaUserRole=new SevaUserRole();

        sevaUserRole.setSevaUser(sevaUserSaved);
        SevaRole sevaRole=new SevaRole();
        //Update Status to Inactive User
        sevaRole.setRoleId(2);
        sevaUserRole.setSevaRole(sevaRole);
        sevaUserRole.setSurCreatedDate(timestamp);
        sevaUserRoleRepository.save(sevaUserRole);

        return sevaUserSaved;
    }
}
