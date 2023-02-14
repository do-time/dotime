package com.jhd.dotime.tasks.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TaskUpdateRequestDto {
    private String title;
    private String content;
}
