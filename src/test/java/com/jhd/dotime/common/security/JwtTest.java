package com.jhd.dotime.common.security;


import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootTest
public class JwtTest {

    @Test
    public void testPasswordEncoding(){
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(16);
        String pwd = encoder.encode("password");
        System.out.println("pwd = " + pwd);
    }
}
