package com.jhd.dotime.chat.dto;

import com.jhd.dotime.chat.entity.ChatRoom;
import io.swagger.annotations.ApiModel;
import lombok.*;


@NoArgsConstructor
public class ChatRoomDto {
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    @ApiModel(value = "chat room 요청")
    public static class Request{
        @NonNull
        private String name;
    }

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    @ApiModel(value = "chat room 응답")
    public static class Response {
        @NonNull
        private String name;

        @NonNull
        private String roomId;

        public static Response of(ChatRoom chatRoom) {
            return Response.builder()
                    .roomId(chatRoom.getId().toString())
                    .name(chatRoom.getRoomname())
                    .build();
        }
    }
}
