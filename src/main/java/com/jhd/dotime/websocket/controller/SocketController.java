package com.jhd.dotime.websocket.controller;


import com.jhd.dotime.chat.dto.ChatMessageDto;
import com.jhd.dotime.chat.entity.ChatMessage;
import com.jhd.dotime.members.entity.Member;
import com.jhd.dotime.websocket.dto.ChatMessageSocketDto;
import com.jhd.dotime.websocket.dto.ChatRoomSocketDto;
import com.jhd.dotime.websocket.service.SocketService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Slf4j
@Controller
@RequiredArgsConstructor
public class SocketController {
    private final SocketService socketService;
    private final SimpMessagingTemplate messagingTemplate;

    @MessageMapping("/chat/enter")
    public void enterUsers(ChatRoomSocketDto dto) {
        socketService.userEnter(dto);
    }

    @MessageMapping("/chat/exit")
    public void userQuits(ChatRoomSocketDto dto) {
        socketService.userQuit(dto);
    }

    @MessageMapping("/chat/message")
    public void sendMessage(ChatMessageSocketDto dto) {socketService.sendMessage(dto);}
}
