package com.example.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class EncrytedPasswordUtils {

    // Encryte Password with BCryptPasswordEncoder
    public static String encrytePassword(String password) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.encode(password);
    }

    public static void main(String[] args) {
        String password = "123";
        String encrytedPassword = encrytePassword(password);
        String encrytedPassword1 = encrytePassword(password);

        System.out.println(encrytedPassword.endsWith(encrytedPassword1));
    }

}
