package com.jhd.dotime.members.dto;

import com.jhd.dotime.members.entity.Member;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MemberDto {
    private String email;

    private String username;

    private String password;

    private String profileImage;

    public Member toEntity(){
        return Member.builder().email(email).username(username).password(password).profileImage(profileImage).build();
    }
}
