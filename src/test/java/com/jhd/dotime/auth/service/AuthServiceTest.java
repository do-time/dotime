package com.jhd.dotime.auth.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jhd.dotime.auth.dto.LoginDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.transaction.annotation.Transactional;

import static org.hamcrest.Matchers.notNullValue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
@Transactional
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class AuthServiceTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    AuthService authService;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    @DisplayName("로그인 성공 테스트")
    public void loginSuccess() throws Exception{
        //given
        LoginDto.Request loginRequestDto = LoginDto.Request.builder()
                .email("test@email.com")
                .password("1234")
                .build();


        //when
        ResultActions result = mockMvc.perform(
                post("/auth/login")
                        .content(objectMapper.writeValueAsString(loginRequestDto))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
        );

        //then
        result.andExpect(status().isOk())
                .andExpect(jsonPath("$.accessToken", notNullValue()));
    }
}