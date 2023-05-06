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
    }
}
