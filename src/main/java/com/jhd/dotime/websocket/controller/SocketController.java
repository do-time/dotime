package com.jhd.dotime.websocket.controller;


import com.jhd.dotime.chat.dto.ChatMessageDto;
import com.jhd.dotime.chat.entity.ChatMessage;
import com.jhd.dotime.members.entity.Member;
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
//    private final SocketService socketService;
    private final SimpMessagingTemplate messagingTemplate;

    @MessageMapping(value="/chat/enter")
    public void enterChatRoom(ChatMessageDto.Request chatRequest){
        System.out.println("연결성공");
        ChatMessage chatMessage = ChatMessage.builder().content("지훈스" + "님이 채팅방에 참여하셨습니다.").sender(null).build();
        messagingTemplate.convertAndSend("/sub/chat/room/" + chatRequest.getRoomId(), chatMessage);
    }


    @MessageMapping(value = "/chat/message")
    public void sendMessage(ChatMessage chatMessage) {
        messagingTemplate.convertAndSend("/sub/chat/room/"+chatMessage.getChatRoom().getId(),chatMessage);
    }
}
