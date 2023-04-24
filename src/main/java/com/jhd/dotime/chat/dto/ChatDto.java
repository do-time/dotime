package com.jhd.dotime.chat.dto;


import io.swagger.annotations.ApiModel;
import lombok.*;

@RequiredArgsConstructor
public class ChatDto {


    @Getter
    @NoArgsConstructor
    @Builder @ApiModel(value = "chat message 요청")
    public static class Request{

    }
}
