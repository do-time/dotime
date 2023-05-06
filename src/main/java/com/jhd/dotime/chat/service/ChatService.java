package com.jhd.dotime.chat.service;

import com.jhd.dotime.chat.dto.ChatRoomDto;
import com.jhd.dotime.chat.entity.ChatRoom;
import org.springframework.web.socket.WebSocketSession;

public interface ChatService {
    ChatRoom findRoom(String roomId);

    ChatRoom createRoom(ChatRoomDto.Request request);

    ChatRoomDto.Response enterChatRoom(ChatRoomDto.Request chatRoomRequest);

    void leaveChatRoom(String roomId);

}
