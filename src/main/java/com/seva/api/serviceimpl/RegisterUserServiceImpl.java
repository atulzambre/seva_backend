package com.seva.api.serviceimpl;

import com.seva.api.entity.SevaRole;
import com.seva.api.entity.SevaUser;
import com.seva.api.entity.SevaUserRole;
import com.seva.api.entity.SevaUserVerification;
import com.seva.api.repository.SevaUserRepository;
import com.seva.api.repository.SevaUserRoleRepository;
import com.seva.api.repository.SevaUserVerificationRepository;
import com.seva.api.service.RegisterUserService;
import com.seva.api.util.EncodeDecodeBase64Util;
import com.seva.api.util.GenerateEmailUtil;
import com.seva.api.util.GenerateJWTUtil;
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
    @Autowired
    GenerateJWTUtil generateJWTUtil;
    @Autowired
    SevaUserVerificationRepository sevaUserVerificationRepository;
    @Autowired
    GenerateEmailUtil generateEmailUtil;


    @Override
    @Transactional
    public SevaUser registerUser(SevaUser sevaUser) {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        sevaUser.setSuCreatedDate(timestamp);
        sevaUser.setSuPassword(encodeDecodeBase64.encode(sevaUser.getSuPassword()));
        SevaUser sevaUserSaved = sevaUserRepository.save(sevaUser);
        SevaUserRole sevaUserRole = new SevaUserRole();

        sevaUserRole.setSevaUser(sevaUserSaved);
        SevaRole sevaRole = new SevaRole();
        //Update Status to Inactive User
        sevaRole.setRoleId(2);
        sevaUserRole.setSevaRole(sevaRole);
        sevaUserRole.setSurCreatedDate(timestamp);
        sevaUserRoleRepository.save(sevaUserRole);

        int verificationCode = generateJWTUtil.generateVerificationCode();
        SevaUserVerification sevaUserVerification = new SevaUserVerification();
        sevaUserVerification.setSevaUser(sevaUserSaved);
        sevaUserVerification.setSuvCreatedDate(timestamp);
        sevaUserVerification.setSuvCode(verificationCode);
        sevaUserVerificationRepository.save(sevaUserVerification);

        String JWTToken = generateJWTUtil.generateJWTForAccountVerification(sevaUserSaved, verificationCode);
        generateEmailUtil.generateRegistrationVerificationMail(sevaUserSaved, JWTToken);

        return sevaUserSaved;
    }

    @Override
    public SevaUser verifyAccount(String JWTToken) {
        generateJWTUtil.verifyAccountJWT(JWTToken);
        return new SevaUser();
    }
}
