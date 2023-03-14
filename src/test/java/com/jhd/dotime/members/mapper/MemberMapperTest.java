package com.jhd.dotime.members.mapper;

import com.jhd.dotime.members.dto.MemberRequestDto;
import com.jhd.dotime.members.dto.MemberResponseDto;
import com.jhd.dotime.members.entity.Member;
import org.junit.jupiter.api.*;
import org.mapstruct.factory.Mappers;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;

class MemberMapperTest {
    private MemberMapper memberMapper = Mappers.getMapper(MemberMapper.class);

    @Test
    public void toEntityTest(){
        //given
        MemberRequestDto memberRequestDto = MemberRequestDto.builder()
                .email("mapper@email.com")
                .password("1234")
                .username("mapperTest")
                .build();

        //when
        Member member = memberMapper.toEntity(memberRequestDto);
        PasswordEncoder passwordEncoder = NoOpPasswordEncoder.getInstance();

        //then
        assertAll(
                () -> assertTrue(member.getEmail().equals(memberRequestDto.getEmail())),
                () -> assertTrue(passwordEncoder.matches(memberRequestDto.getPassword(), member.getPassword())),
                () -> assertTrue(member.getUsername().equals(memberRequestDto.getUsername()))
        );
    }

    @Test
    public void toResponseDtoTest(){
        //given
        MemberRequestDto memberRequestDto = MemberRequestDto.builder()
                .email("mapper@email.com")
                .password("1234")
                .username("mapperTest")
                .build();

        Member member = memberMapper.toEntity(memberRequestDto);

        //when
        MemberResponseDto memberResponseDto = memberMapper.toResponseDto(member);

        //then
        assertAll(
                () -> assertTrue(memberResponseDto.getEmail().equals(member.getEmail())),
                () -> assertTrue(memberResponseDto.getUsername().equals(member.getUsername()))
        );
    }
}