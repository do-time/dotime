package com.jhd.dotime.members.dto;

import com.jhd.dotime.members.entity.Member;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MemberRequestDto {
    private Long id;

    private String email;

    private String username;

    private String password;

    private String profileImage;

    public MemberRequestDto(String email, String username, String password, String profileImage){
        this.email = email;
        this.username = username;
        this.password = password;
        this.profileImage = profileImage;
    }
}
