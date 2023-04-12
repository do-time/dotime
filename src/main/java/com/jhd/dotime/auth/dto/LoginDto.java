package com.jhd.dotime.auth.dto;



import com.jhd.dotime.members.entity.Member;
import com.sun.istack.NotNull;
import io.swagger.annotations.ApiModel;
import lombok.*;

//@NoArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor
public class LoginDto {
    @Builder
    @Data
    @NoArgsConstructor
    @AllArgsConstructor @ApiModel(value = "login 요청")
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
    @AllArgsConstructor @ApiModel(value = "login 응답")
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
