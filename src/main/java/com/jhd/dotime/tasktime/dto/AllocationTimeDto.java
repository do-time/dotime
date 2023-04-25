package com.jhd.dotime.tasktime.dto;

import com.jhd.dotime.tasktime.entity.TaskTimeLog;
import io.swagger.annotations.ApiModel;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;


public class AllocationTimeDto {
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    @ToString
    @ApiModel(value = "Allocation Time 요청 DTO")
    public static class Request{
        @NonNull
        Long taskId;

        List<Allocation> allocationList;
    }

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    @ToString
    @ApiModel(value = "Allocation Time 반환 DTO")
    public static class Response{
        Long taskId;

        LocalDateTime startTime;

        LocalDateTime endTime;

        public Response(TaskTimeLog taskTimeLog) {
        }
    }

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    @ToString
    @ApiModel(value = "Allocation 입력/수정에 사용되는 Type 구분자")
    public static class Allocation{
        @NonNull
        AllocationType type;

        Long time;
    }
}
