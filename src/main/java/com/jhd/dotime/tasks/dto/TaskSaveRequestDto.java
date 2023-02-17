package com.jhd.dotime.tasks.dto;


import com.jhd.dotime.members.entity.Member;
import com.jhd.dotime.tasks.entity.Task;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class TaskSaveRequestDto {

    private Member member;
    private String title;
    private String content;

//    LocalDateTime createdDate;
//
//    LocalDateTime updatedDate;

    @Builder
    public TaskSaveRequestDto(Member member, String title, String content){
        this.member = member;
        this.title = title;
        this.content = content;
    }
    public Task toEntity(){
        return Task.builder()
                .member(member)
                .title(title)
                .content(content)
                .build();
    }

}
