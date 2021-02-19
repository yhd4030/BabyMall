package com.cmx.mall.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class PasswordEncoderUtil {
    private static PasswordEncoder passwordEncoder=new BCryptPasswordEncoder();

    public static String passwordEncoder(String password){
        return passwordEncoder.encode(password);
    }
}
