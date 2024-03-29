package com.jhd.dotime.tasks.dto;


import com.jhd.dotime.hashtag.dto.HashTagDto;
import com.jhd.dotime.tasks.entity.Task;
import com.jhd.dotime.tasktime.dto.AllocationTimeDto;
import io.swagger.annotations.ApiModel;
import lombok.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TaskDto {


    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder @ApiModel(value = "to-do 요청")
    public static class Request {

        @NonNull
        private String title;

        private String content;

        private String hashtag;

        private List<AllocationTimeDto.Allocation> allocationList;

    }

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder @ApiModel(value = "to-do 응답")
    public static class Response {

        @NonNull
        private Long id;
        private String title;
        private String content;

//        @Builder.Default
        private List<HashTagDto.Response> hashtag;
        private List<AllocationTimeDto.Response>  allocationTime;
        private LocalDateTime createdDate;
        private LocalDateTime updatedDate;

        public static Response of(Task task) {
            return Response.builder()
                    .id(task.getId())
                    .title(task.getTitle())
                    .content(task.getContent())
                    .createdDate(task.getCreatedDate())
                    .updatedDate(task.getUpdatedDate())
                    .hashtag(task.getHashTag().stream().map(HashTagDto.Response::of).collect(Collectors.toList())) // tasktag List를 받아 해시태그 Response로 매핑
                    .allocationTime(task.getAllocationTimeList().stream().map(AllocationTimeDto.Response::of).collect(Collectors.toList()))
                    .build();
        }
    }



}
