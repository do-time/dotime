package com.jhd.dotime.chat.entity;

import com.jhd.dotime.chat.service.ChatService;
import com.jhd.dotime.common.entity.BaseTimeEntity;
import lombok.*;
import org.springframework.web.socket.WebSocketSession;

import javax.persistence.*;
import java.io.IOException;
import java.util.*;


@NoArgsConstructor
@AllArgsConstructor
@Entity(name="chatroom")
@Getter
public class ChatRoom extends BaseTimeEntity {

    @Id
    @Column(name="room_id")
    private String id;

    @Column(name="name")
    private String name;

    @Builder
    public ChatRoom(String name) {
        this.id = UUID.randomUUID().toString();
        this.name = name;

    }

    public void handle(@NonNull WebSocketSession session, ChatMessage chatMessage, ChatService chatService) throws IOException {

    }

    public <T> void sendMessage(WebSocketSession session, T message){

    }
}
