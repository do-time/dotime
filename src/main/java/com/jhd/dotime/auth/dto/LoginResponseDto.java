package com.jhd.dotime.auth.dto;


import com.jhd.dotime.members.entity.Member;
import lombok.*;

@Builder
@Data
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class LoginResponseDto {

    private String accessToken;

//    public static LoginResponseDto of(Member member){
//        if(member == null) return null;
//
//        return LoginResponseDto.
//    }
}
