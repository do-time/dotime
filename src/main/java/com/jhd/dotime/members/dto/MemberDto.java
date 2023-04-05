package com.jhd.dotime.members.dto;


import com.jhd.dotime.members.entity.Member;
import com.jhd.dotime.tasks.dto.TaskDto;
import lombok.*;

import java.util.List;

@NoArgsConstructor
public class MemberDto {
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class Request {
        private Long id;

        private String email;

        private String username;

        private String password;

        private String profileImage;

        public Request(String email, String username, String password, String profileImage){
            this.email = email;
            this.username = username;
            this.password = password;
            this.profileImage = profileImage;
        }
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class Response {
        private Long id;

        private String email;

        private String username;

        private String profileImage;

        private List<TaskDto.Response> taskList;

        public Response(Member member){
            this.id = member.getId();
            this.email = member.getEmail();
            this.username = member.getUsername();
            this.profileImage = member.getProfileImage();
        }
    }


}
