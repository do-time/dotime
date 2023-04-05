package com.jhd.dotime.members.mapper;

import com.jhd.dotime.auth.entity.Authority;
import com.jhd.dotime.members.dto.MemberDto;
import com.jhd.dotime.members.dto.MemberDto.Request;
import com.jhd.dotime.members.dto.MemberDto.Response;
import com.jhd.dotime.members.entity.Member;
import org.junit.jupiter.api.*;
import org.mapstruct.factory.Mappers;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;

class MemberMapperTest {
    private MemberMapper memberMapper = Mappers.getMapper(MemberMapper.class);

    @Test
    public void toEntityTest(){
        //given
        MemberDto.Request memberDtoRequest = MemberDto.Request.builder()
                .email("mapper@email.com")
                .password("1234")
                .username("mapperTest")
                .build();

        //when
        Member member = memberMapper.toEntity(memberDtoRequest);
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        System.out.println(member.toString());

        //then
        assertAll(
                () -> assertTrue(member.getEmail().equals(memberDtoRequest.getEmail())),
                () -> assertTrue(passwordEncoder.matches(memberDtoRequest.getPassword(), member.getPassword())),
                () -> assertTrue(member.getUsername().equals(memberDtoRequest.getUsername()))
                //() -> assertTrue(member.getAuthorities().equals(MemberDto.Request.getUsername()))
        );
    }

    @Test
    public void toResponseDtoTest(){
        //given
        MemberDto.Request memberDtoRequest = MemberDto.Request.builder()
                .email("mapper@email.com")
                .password("1234")
                .username("mapperTest")
                .build();

        Member member = memberMapper.toEntity(memberDtoRequest);

        //when
        MemberDto.Response memberDtoResponse = memberMapper.toResponseDto(member);

        //then
        assertAll(
                () -> assertTrue(memberDtoResponse.getEmail().equals(member.getEmail())),
                () -> assertTrue(memberDtoResponse.getUsername().equals(member.getUsername()))
        );
    }
}