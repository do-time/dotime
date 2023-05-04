package com.jhd.dotime.tasktime.dto;

import com.jhd.dotime.tasktime.entity.AllocationTime;
import io.swagger.annotations.ApiModel;
import lombok.*;

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
        Long id;

        String category;

        Long hour;

        public static AllocationTimeDto.Response of(AllocationTime allocationTime){
            return Response.builder()
                    .id(allocationTime.getId())
                    .category(allocationTime.getCategory().getValue())
                    .hour(allocationTime.getHour())
                    .build();
        }
    }

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    @ToString
    public static class Allocation{
        @NonNull
        AllocationCategory category;

        Long time;
    }
}
