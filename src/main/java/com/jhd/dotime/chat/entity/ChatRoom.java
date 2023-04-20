package com.jhd.dotime.chat.entity;

import com.jhd.dotime.chat.service.ChatService;
import com.jhd.dotime.common.entity.BaseTimeEntity;
import lombok.*;
import org.springframework.web.socket.WebSocketSession;

import javax.persistence.*;
import java.util.*;


@NoArgsConstructor
@AllArgsConstructor
@Entity(name="chatroom")
@Getter
public class ChatRoom extends BaseTimeEntity {

    @Id
    @Column(name="room_id")
    private String roomId;

    @Column(name="name")
    private String name;


    @Builder
    public ChatRoom(String name) {
        this.roomId = UUID.randomUUID().toString();
        this.name = name;

    }

    public void handle(WebSocketSession session, Chat chatMessage, ChatService chatService) {

    }

    public <T> void sendMessage(WebSocketSession session, T message){

    }
}
