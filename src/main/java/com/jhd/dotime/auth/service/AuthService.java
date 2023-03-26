package com.jhd.dotime.auth.service;


import com.jhd.dotime.auth.dto.LoginRequestDto;
import com.jhd.dotime.auth.dto.LoginResponseDto;
import com.jhd.dotime.common.security.TokenProvider;
import com.jhd.dotime.members.repository.MemberRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthService {
    private final TokenProvider tokenProvider;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final MemberRepository memberRepository;

    public LoginResponseDto authenticate(LoginRequestDto loginRequestDto){
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(loginRequestDto.getEmail(), loginRequestDto.getPassword());

        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = tokenProvider.createToken(authentication);

        return LoginResponseDto.builder()
                .accessToken(token)
                .build();
    }

}
