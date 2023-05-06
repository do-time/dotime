package com.jhd.dotime.tasktime.dto;

import com.jhd.dotime.tasktime.entity.TaskTimeLog;
import io.swagger.annotations.ApiModel;
import lombok.*;

import java.time.LocalDateTime;

public class TaskTimeLogDto {
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    @ToString
    @ApiModel(value = "task log 요청")
    public static class Request{
        Long id;

        @NonNull
        Long taskId;

        LocalDateTime startTime;

        LocalDateTime endTime;
    }

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    @ToString
    @ApiModel(value = "task log 반환")
    public static class Response{
        Long id;

        Long taskId;

        LocalDateTime startTime;

        LocalDateTime endTime;

        public Response(TaskTimeLog taskTimeLog) {
        }
    }
}
