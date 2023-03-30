package com.jhd.dotime.hashtag.dto;

import com.jhd.dotime.hashtag.entity.HashTag;
import lombok.*;

import java.util.ArrayList;
import java.util.List;


@NoArgsConstructor
public class HashTagDto {

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class Request{
        private String name;
        @Builder.Default
        private List<Long> hashTagId = new ArrayList<>();
    }

    @Getter
    @NoArgsConstructor@AllArgsConstructor
    @Builder
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