package com.jhd.dotime.chat.dto;


import io.swagger.annotations.ApiModel;
import lombok.*;

@RequiredArgsConstructor
public class ChatMessageDto {

    @Getter
    @RequiredArgsConstructor
    @NoArgsConstructor
    @Builder @ApiModel(value = "chat message 요청")
    public static class Request{
        @NonNull
        private String content;

        @NonNull
        private String roomId;

        @NonNull
        private Long senderId;
    }


    @Getter
    @NoArgsConstructor
    @RequiredArgsConstructor
    @Builder
    @ApiModel(value = "chat message 응답")
    public static class Response{
//        private String memberId;
        @NonNull
        private String roomId;

        @NonNull
        private String content;

        @NonNull
        private Long senderId;
    }
}
