package com.jhd.dotime.chat.service;

import com.jhd.dotime.chat.dto.ChatRoomDto;
import com.jhd.dotime.chat.entity.ChatRoom;
import com.jhd.dotime.members.entity.Member;
import org.springframework.web.socket.WebSocketSession;

import java.util.List;

public interface ChatService {
    ChatRoom findRoom(String roomId);

    ChatRoom createRoom(ChatRoomDto.Request request);

    ChatRoomDto.Response enterChatRoom(ChatRoomDto.Request chatRoomRequest);

    void leaveChatRoom(String roomId);

}
