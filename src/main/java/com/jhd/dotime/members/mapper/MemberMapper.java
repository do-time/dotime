package com.jhd.dotime.members.mapper;

import com.jhd.dotime.auth.entity.Authority;
import com.jhd.dotime.members.dto.MemberRequestDto;
import com.jhd.dotime.members.dto.MemberResponseDto;
import com.jhd.dotime.members.entity.Member;
import org.mapstruct.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Set;

@Mapper(
        componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR, // 생성자 주입 전략
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface MemberMapper {

    @Mapping(
            source = "password", target = "password",
            qualifiedByName = "encryptPassword"
    )
    @Mapping(target = "authorities", expression = "java(mapAuthority())")
    Member toEntity(MemberRequestDto memberRequestDto);

    @Mapping(target = "taskList", ignore = true)
    MemberResponseDto toResponseDto(Member member);

    @Named("encryptPassword")
    default String encryptPassword(String password){
        return new BCryptPasswordEncoder().encode(password);
    }

    default Set<Authority> mapAuthority(){
        Authority authority = Authority.builder()
                .authorityName("USER_ROLE")
                .build();
        return Collections.singleton(authority);
    }
}
