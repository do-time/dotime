package com.jhd.dotime.chat.service;

import com.jhd.dotime.chat.entity.ChatRoom;
import org.springframework.web.socket.WebSocketSession;

public interface ChatService {
    ChatRoom findRoom(String roomId);

    ChatRoom createRoom(String name);

    <T> void sendMessage(WebSocketSession session, T message);

}
