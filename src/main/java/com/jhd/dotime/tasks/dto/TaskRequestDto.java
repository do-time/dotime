package com.jhd.dotime.tasks.dto;


import com.jhd.dotime.members.entity.Member;
import com.jhd.dotime.tasks.entity.Task;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class TaskRequestDto {

    private String title;
    private String content;
    private String hashtag;

    @Builder
    public TaskRequestDto(String title, String content, String hashtag){
        this.title = title;
        this.content = content;
        this.hashtag = hashtag;
    }
    public Task toEntity(Member member){
        return Task.builder()
                .member(member)
                .title(title)
                .content(content)
                .hashtag(hashtag)
                .build();
    }

}
