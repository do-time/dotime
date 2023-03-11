package com.jhd.dotime.members.dto;

import com.jhd.dotime.auth.entity.Auth;
import com.jhd.dotime.members.entity.Member;
import lombok.*;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Collections;

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

    public MemberRequestDto(String email, String username, String password, String profileImage) {
        this.email = email;
        this.username = username;
        this.password = password;
        this.profileImage = profileImage;
    }

    public Member toEntity() {
        Auth auth = Auth.builder()
                .authorityName("ROLE_USER")
                .build();


        return Member.builder()
                .id(id)
                .email(email)
                .username(username)
                .password(password)
                .profileImage(profileImage)
                .authorities(Collections.singleton(auth))
                .build();
    }

    public void passwordEncoding(PasswordEncoder passwordEncoder){
        this.password = passwordEncoder.encode(this.password);
    }
}
