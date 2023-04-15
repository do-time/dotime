package com.jhd.dotime.tasktimelog.dto;

import io.swagger.annotations.ApiModel;
import lombok.*;

public class TaskTimeLogDto {
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    @ApiModel(value = "task log 요청")
    public static class Request{
        @NonNull
        Long id;
    }
}
