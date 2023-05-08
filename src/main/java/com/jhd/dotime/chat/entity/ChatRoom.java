package com.jhd.dotime.chat.entity;

import com.jhd.dotime.chat.service.ChatService;
import com.jhd.dotime.common.entity.BaseTimeEntity;
import com.jhd.dotime.members.entity.Member;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "chatroom_id")
    private Long id;

    @Column(name = "roomname")
    private String roomname;

    @Column
    private String lastMessage;

    @Builder
    public ChatRoom(String roomname) {
        this.roomname = roomname;
    }

    public void updateLastMessage(String lastMessage) {
        this.lastMessage = Objects.requireNonNull(lastMessage);
    }

}