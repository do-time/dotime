package com.jhd.dotime.common.config.jwt;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/*
 * JWT 설정파일
 * TokenProvider에 의존성을 주입하고 빈을 생성하는 역할
 */
@Configuration
public class JwtConfig {
    @Value("${jwt.secret}")
    private String accessTokenSecret;
    @Value("${jwt.access-token-validity-in-seconds}")
    private Long accessTokenValidityInSeconds;

    // 액세스 토큰 발급용, 리프레시 토큰 발급용은 각각 별도의 키와 유효기간을 갖는다.
    @Bean(name = "tokenProvider")
    public JwtTokenProvider jwtTokenProvider() {
        return new JwtTokenProvider(accessTokenSecret, accessTokenValidityInSeconds);
    }
}
