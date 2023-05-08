package com.jhd.dotime.websocket.dto;

import java.util.ArrayList;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ChatMessageSocketDto {
    private String chatRoomId;
    private String chatUserId;
    private String content;
    private ArrayList<String> chatRoomMembers;
//    private ArrayList<String> readMembers;
    private ArrayList<String> chatImages;
    private String messageType;

    @Builder
    public ChatMessageSocketDto(String chatRoomId, String chatUserId, String content, ArrayList<String> chatRoomMembers, ArrayList<String> chatImages,
                                String messageType) {
        this.chatRoomId = chatRoomId;
        this.chatUserId = chatUserId;
        this.content = content;
        this.chatRoomMembers = chatRoomMembers;
        this.chatImages = chatImages;
        this.messageType = messageType;
    }

//    @Builder
//    public ChatMessageSocketDto(String chatRoomId, String chatUserId, String content, ArrayList<String> chatRoomMembers, ArrayList<String> readMembers, ArrayList<String> chatImages,
//                                String messageType) {
//        this.chatRoomId = chatRoomId;
//        this.chatUserId = chatUserId;
//        this.content = content;
//        this.chatRoomMembers = chatRoomMembers;
//        this.readMembers = readMembers;
//        this.chatImages = chatImages;
//        this.messageType = messageType;
//    }
}