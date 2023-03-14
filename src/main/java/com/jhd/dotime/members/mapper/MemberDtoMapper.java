package com.jhd.dotime.members.mapper;

import com.jhd.dotime.members.dto.MemberRequestDto;
import com.jhd.dotime.members.entity.Member;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Mapper(
        componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR // 생성자 주입 전략

)
public interface MemberDtoMapper {

    @Mapping(
            source = "password", target = "password",
            qualifiedByName = "encryptPassword"
    )
    Member toEntity(MemberRequestDto memberRequestDto);

    @Named("encryptPassword")
    default String encryptPassword(String password){
        return new BCryptPasswordEncoder().encode(password);
    }
}
