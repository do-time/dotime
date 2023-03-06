package com.jhd.dotime.members.dto;

import com.jhd.dotime.members.entity.Member;
import com.jhd.dotime.tasks.dto.TaskResponseDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class MemberResponseDto {
    private Long id;

    private String email;

    private String username;

    private String profileImage;

    private List<TaskResponseDto> taskList;

    public MemberResponseDto(Member member){
        this.id = member.getId();
        this.email = member.getEmail();
        this.username = member.getUsername();
        this.profileImage = member.getProfileImage();
    }
}
