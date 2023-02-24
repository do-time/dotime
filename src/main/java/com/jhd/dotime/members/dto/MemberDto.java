package com.jhd.dotime.members.dto;

import com.jhd.dotime.members.entity.Member;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MemberDto {
    private Long id;

    private String email;

    private String username;

    private String password;

    private String profileImage;

    public Member toEntity(){
        return Member.builder().id(id).email(email).username(username).password(password).profileImage(profileImage).build();
    }
}
