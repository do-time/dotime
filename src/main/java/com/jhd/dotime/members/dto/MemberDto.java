package com.jhd.dotime.members.dto;

import com.jhd.dotime.members.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class MemberDto {
    private String email;

    private String username;

    private String password;

    private String profileImage;

    public Member toEntity() {
        return Member.builder()
                .email(email)
                .username(username)
                .password(password)
                .profileImage(profileImage)
                .build();
    }
}
