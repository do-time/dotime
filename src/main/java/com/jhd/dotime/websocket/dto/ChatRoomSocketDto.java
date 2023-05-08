package com.jhd.dotime.websocket.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ChatRoomSocketDto {
    private String chatMemberId;
    private String chatRoomId;

    @Builder
    public ChatRoomSocketDto(String chatMemberId, String chatRoomId) {
        this.chatMemberId = chatMemberId;
        this.chatRoomId = chatRoomId;
    }

    @Override
    public String toString() {
        return "ChatRoomSocketDto{" +
                "chatMemberId='" + chatMemberId + '\'' +
                ", chatRoomId='" + chatRoomId + '\'' +
                '}';
    }
}