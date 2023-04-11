package com.jhd.dotime.auth.dto;



import com.jhd.dotime.members.entity.Member;
import com.sun.istack.NotNull;
import lombok.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class LoginDto {
    @Builder
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Request {
        @NotNull
        private String email;

        @NotNull
        private String password;
    }

    @Builder
    @Data
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Response {

        private String accessToken;

//        public static Response of(Member member) {
//            if (member == null) return null;
//
//            return Response.builder()
//                    .accessToken()
//        }
    }


}
