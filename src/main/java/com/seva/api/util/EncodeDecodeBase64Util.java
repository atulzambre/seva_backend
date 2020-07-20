package com.seva.api.util;

import org.springframework.stereotype.Component;

import java.util.Base64;

@Component
public class EncodeDecodeBase64Util {

    public String encode(String str) {
        return Base64.getEncoder().encodeToString(str.getBytes());
    }

    public String decode(String str) {
        byte[] decodedBytes = Base64.getDecoder().decode(str);
        String decodedString = new String(decodedBytes);
        return decodedString;
    }

}
