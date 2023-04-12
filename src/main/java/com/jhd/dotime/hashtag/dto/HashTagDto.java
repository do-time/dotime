package com.jhd.dotime.hashtag.dto;

import com.jhd.dotime.hashtag.entity.HashTag;
import io.swagger.annotations.ApiModel;
import lombok.*;

import java.util.ArrayList;
import java.util.List;


@NoArgsConstructor
public class HashTagDto {

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder @ApiModel(value = "hashtag 요청")
    public static class Request{
        private String name;
        @Builder.Default // default를 사용하지 않으면 arrayList로 초기화되지 않을수도 있다.
        private List<Long> hashTagId = new ArrayList<>();
    }

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder @ApiModel(value = "hashtag 응답")
    public static class Response{
        @NonNull
        private Long id;
        private String name;

        public static Response of(HashTag hashTag){
            return Response.builder()
                    .id(hashTag.getId())
                    .name(hashTag.getName())
                    .build();
        }
    }
}