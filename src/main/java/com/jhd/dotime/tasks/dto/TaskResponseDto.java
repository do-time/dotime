package com.jhd.dotime.tasks.dto;


import com.jhd.dotime.tasks.entity.Task;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
public class TaskResponseDto {
    private final Long id;
    private final String title;
    private final String content;
    private final LocalDateTime createdDate;
    private final LocalDateTime updatedDate;

    private final String hashtag;

    public TaskResponseDto(Task entity){
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.content = entity.getContent();
        this.createdDate = entity.getCreatedDate();
        this.updatedDate = entity.getUpdatedDate();
        this.hashtag = entity.getHashtag();

    }
}
