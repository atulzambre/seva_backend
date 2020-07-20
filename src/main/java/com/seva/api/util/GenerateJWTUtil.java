package com.seva.api.util;


import com.seva.api.entity.SevaUser;
import com.seva.api.entity.SevaUserVerification;
import com.seva.api.repository.SevaUserVerificationRepository;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;
import java.util.Random;

@Component
public class GenerateJWTUtil {

    @Autowired
    ReadPropertiesUtil readPropertiesUtil;

    @Autowired
    SevaUserVerificationRepository sevaUserVerificationRepository;

    public String generateJWTForAccountVerification(SevaUser sevaUser, int verificationCode) {
        String token = null;
        SecretKey key = getSecretKey();
        Date expireDate = generateExpirationDate();
        token = Jwts.builder().signWith(key, SignatureAlgorithm.HS256).setIssuer("sevamandal").setSubject(sevaUser.getSuId().toString()).setExpiration(expireDate).setIssuedAt(new Date()).claim("validateAccount", verificationCode).compact();
        return token;
    }


    private SecretKey getSecretKey() {
        String secret = readPropertiesUtil.getSecretKey();
        SecretKey key = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
        return key;
    }

    private Date generateExpirationDate() {
        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, 3);
        date = calendar.getTime();
        return date;
    }

    public int generateVerificationCode() {
        Random r = new Random(System.currentTimeMillis());
        return ((1 + r.nextInt(2)) * 10000 + r.nextInt(10000));
    }


    public Boolean verifyAccountJWT(String JWTToken) {
        try {
            Key key = Keys.hmacShaKeyFor(readPropertiesUtil.getSecretKey().getBytes(StandardCharsets.UTF_8));
            if (!Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(JWTToken).getBody().getIssuer().equals("sevamandal")) {
                return false;
            } else if (Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(JWTToken).getBody().getExpiration().before(new Date())) {
                return false;
            } else {
                String su_id = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(JWTToken).getBody().getSubject();
                String verificationCode = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(JWTToken).getBody().get("validateAccount").toString();

                return validateVerifcation(su_id, verificationCode);
            }
        } catch (JwtException e) {
            return false;
        }
    }

    private Boolean validateVerifcation(String su_id, String verificationCode) {
        SevaUserVerification sevaUserVerification = sevaUserVerificationRepository.findSevaUserVerification(Long.parseLong(su_id), Integer.parseInt(verificationCode));
        if(!Objects.isNull(sevaUserVerification)){
            sevaUserVerificationRepository.deleteAllBySevaUserSuId(Long.parseLong(su_id));
            return true;
        }
        else{
            return false;
        }
    }


}
