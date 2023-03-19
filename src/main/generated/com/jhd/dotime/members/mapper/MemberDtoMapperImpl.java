package com.jhd.dotime.members.mapper;

import com.jhd.dotime.members.dto.MemberRequestDto;
import com.jhd.dotime.members.entity.Member;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-03-15T21:49:24+0900",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 11.0.11 (AdoptOpenJDK)"
)
@Component
public class MemberDtoMapperImpl implements MemberDtoMapper {

    @Override
    public Member toEntity(MemberRequestDto memberRequestDto) {
        if ( memberRequestDto == null ) {
            return null;
        }

        Member member = new Member();

        member.setPassword( encryptPassword( memberRequestDto.getPassword() ) );
        member.setId( memberRequestDto.getId() );
        member.setEmail( memberRequestDto.getEmail() );
        member.setUsername( memberRequestDto.getUsername() );
        member.setProfileImage( memberRequestDto.getProfileImage() );

        return member;
    }
}
