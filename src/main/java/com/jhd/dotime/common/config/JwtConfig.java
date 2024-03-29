package com.jhd.dotime.common.config;

import com.jhd.dotime.common.security.TokenProvider;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetailsService;

/*
 * JWT 설정파일
 * TokenProvider에 의존성을 주입하고 빈을 생성하는 역할
 */
@Configuration
@ComponentScan
public class JwtConfig {
    @Value("${jwt.secret}")
    private String accessTokenSecret;
    @Value("${jwt.access-token-validity-in-seconds}")
    private Long accessTokenValidityInSeconds;

    private final UserDetailsService userDetailsService;

    public JwtConfig(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    // 액세스 토큰 발급용, 리프레시 토큰 발급용은 각각 별도의 키와 유효기간을 갖는다.
    @Bean(name = "tokenProvider")
    public TokenProvider tokenProvider() {
        return new TokenProvider(accessTokenSecret, accessTokenValidityInSeconds, userDetailsService);
    }
}