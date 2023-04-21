package com.jhd.dotime.chat.service;

import com.jhd.dotime.chat.entity.ChatRoom;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.WebSocketSession;

@Service
@RequiredArgsConstructor
public class ChatServiceImpl implements ChatService{
    @Override
    public ChatRoom findRoom(String roomId) {
        return null;
    }

    @Override
    public ChatRoom createRoom(String name) {
        return null;
    }

    @Override
    public <T> void sendMessage(WebSocketSession session, T message) {

    }
}
