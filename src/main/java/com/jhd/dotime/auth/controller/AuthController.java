package com.jhd.dotime.auth.controller;

import com.jhd.dotime.auth.dto.LoginRequestDto;
import com.jhd.dotime.auth.dto.LoginResponseDto;
import com.jhd.dotime.auth.service.AuthService;
import com.jhd.dotime.common.config.jwt.JwtFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/login") // Account 인증 API
    public ResponseEntity<?> authorize(@RequestBody LoginRequestDto loginDto) {

        LoginResponseDto token = authService.login(loginDto);

        // response header 에도 넣고 응답 객체에도 넣는다.
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(JwtFilter.AUTHORIZATION_HEADER, "Bearer " + token.getAccessToken());

        return new ResponseEntity<>(token, httpHeaders, HttpStatus.CREATED);
    }
}