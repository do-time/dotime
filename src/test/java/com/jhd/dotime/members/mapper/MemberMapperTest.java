package com.jhd.dotime.members.mapper;

import com.jhd.dotime.members.dto.MemberRequestDto;
import com.jhd.dotime.members.entity.Member;
import org.junit.jupiter.api.*;
import org.mapstruct.factory.Mappers;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;

class MemberMapperTest {
    private MemberDtoMapper memberDtoMapper = Mappers.getMapper(MemberDtoMapper.class);

    @Test
    public void toEntityTest(){
        //given
        MemberRequestDto memberRequestDto = MemberRequestDto.builder()
                .email("mapper@email.com")
                .password("1234")
                .username("mapperTest")
                .build();

        //when
        Member member = memberDtoMapper.toEntity(memberRequestDto);
        PasswordEncoder passwordEncoder = NoOpPasswordEncoder.getInstance();

        //then
        assertAll(
                () -> assertTrue(member.getEmail().equals(memberRequestDto.getEmail())),
                () -> assertTrue(passwordEncoder.matches(memberRequestDto.getPassword(), member.getPassword())),
                () -> assertTrue(member.getUsername().equals(memberRequestDto.getUsername()))
        );
    }
}