package com.jhd.dotime.chat.controller;


import com.jhd.dotime.chat.dto.ChatRoomDto;
import com.jhd.dotime.chat.entity.ChatRoom;
import com.jhd.dotime.chat.service.ChatRoomService;
import com.jhd.dotime.chat.service.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/chat")
public class ChatController {

    private final ChatRoomService chatService;


    @PostMapping
    public List<ChatRoomDto.Response> createRoom(@RequestParam String name, ChatRoomDto.Request chatRoomRequestDto) {
        chatService.createChatroom(chatRoomRequestDto);
        return null;
    }


    @GetMapping
    public List<ChatRoomDto.Response> findAllRoom() {
        return chatService.findAllRoom();
    }

}