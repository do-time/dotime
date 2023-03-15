package com.jhd.dotime.common.security;


import com.jhd.dotime.common.config.JwtConfig;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.ActiveProfiles;


@ActiveProfiles("test")
@SpringBootTest
public class JwtTest {

    @Test
    public void testPasswordEncoding(){
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(16);

        String pwd = encoder.encode("1234");
        System.out.println(BCrypt.checkpw("1234", pwd));


        System.out.println("pwd = " + pwd);
    }
}
