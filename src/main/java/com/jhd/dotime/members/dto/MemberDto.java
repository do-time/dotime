package com.jhd.dotime.members.dto;


import com.jhd.dotime.members.entity.Member;
import com.jhd.dotime.tasks.dto.TaskDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.List;


@NoArgsConstructor
public class MemberDto {

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder @ApiModel(value = "멤버 요청")
    public static class Request {
        private Long id;

        @NotEmpty(message = "이메일 입력은 필수입니다.")
        @Pattern(regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+.[A-Za-z]{2,6}$", message = "이메일 형식에 맞지 않습니다.")
        private String email;

        @NotEmpty(message = "이름 입력은 필수입니다.")
        private String username;

        @NotEmpty(message = "비밀번호 입력은 필수입니다.")
        //@Size(min = 5, message = "비밀번호는 최소 5자 이상이어야 합니다.")
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
    @Builder @ApiModel(value = "멤버 응답")
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
