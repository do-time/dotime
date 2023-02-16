package com.jhd.dotime.members.dto;

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

    private String email;

    private String username;

    private String password;

    private String profileImage;

    private List<TaskResponseDto> taskList;

}
