package com.jhd.dotime.chat.service;

import com.jhd.dotime.chat.dto.ChatRoomDto;
import com.jhd.dotime.chat.entity.ChatRoom;

import java.util.List;

public interface ChatRoomService {

    List<ChatRoomDto.Response> createChatroom(ChatRoomDto.Request chatroomRequestDto);
    List<ChatRoomDto.Response> findAllRoom();
}
