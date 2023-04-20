package com.jhd.dotime.tasktime.dto;

import com.jhd.dotime.tasktime.entity.TaskTimeLog;
import io.swagger.annotations.ApiModel;
import lombok.*;

import java.time.LocalDateTime;

/*
 * 수정예정
 * type Enum으로 변경
 * day, week, month, year type 한 번에 요청이 들어올 수 있게 DTO 변경
 */
public class AllocationTimeDto {
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    @ToString
    @ApiModel(value = "Allocation Time 요청 DTO")
    public static class Request{
        Long id;

        @NonNull
        Long taskId;

        String type;

        Long time;
    }

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    @ToString
    @ApiModel(value = "Allocation Time 반환 DTO")
    public static class Response{
        Long id;

        Long taskId;

        LocalDateTime startTime;

        LocalDateTime endTime;

        public Response(TaskTimeLog taskTimeLog) {
        }
    }
}
