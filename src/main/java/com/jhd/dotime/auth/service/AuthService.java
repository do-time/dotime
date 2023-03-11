package com.jhd.dotime.auth.service;

import com.jhd.dotime.auth.dto.LoginRequestDto;
import com.jhd.dotime.auth.dto.LoginResponseDto;

public interface AuthService {
    LoginResponseDto login(LoginRequestDto loginRequestDto) ;

}
