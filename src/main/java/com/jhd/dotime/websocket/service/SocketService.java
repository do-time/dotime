package com.jhd.dotime.websocket.service;

import com.jhd.dotime.chat.dto.ChatMessageDto;
import com.jhd.dotime.chat.entity.ChatMessage;
import com.jhd.dotime.chat.entity.ChatRoom;
import com.jhd.dotime.chat.entity.MemberChatRoom;
import com.jhd.dotime.chat.repository.ChatMessageRepository;
import com.jhd.dotime.chat.repository.ChatRoomRepository;
import com.jhd.dotime.chat.repository.MemberChatRoomRepository;
import com.jhd.dotime.common.exception.CustomException;
import com.jhd.dotime.members.common.error.MemberErrorCode;
import com.jhd.dotime.members.common.exception.MemberExceptionHandler;
import com.jhd.dotime.members.entity.Member;
import com.jhd.dotime.members.repository.MemberRepository;
import com.jhd.dotime.websocket.dto.ChatMessageSocketDto;
import com.jhd.dotime.websocket.dto.ChatRoomSocketDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class SocketService {

    private final SimpMessageSendingOperations messageSender;
    private final MemberRepository memberRepository;
    private final ChatRoomRepository chatRoomRepository;
    private final ChatMessageRepository chatMessageRepository;
    private final MemberChatRoomRepository memberChatRoomRepository;



    @Transactional(isolation = Isolation.SERIALIZABLE)
    public void userEnter(ChatRoomSocketDto dto){
        log.info("User {} entered {} room", dto.getChatMemberId(), dto.getChatRoomId());
        String roomId = dto.getChatRoomId();
        String userId = dto.getChatMemberId();

        System.out.println(roomId + " " + userId);

        Member member = memberRepository.findById(Long.parseLong(userId)).orElseThrow(() -> new CustomException(MemberErrorCode.MEMBER_NOT_FOUND));
        ChatRoom chatRoom = chatRoomRepository.findChatRoomById(Long.parseLong(roomId)).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 채팅방입니다."));
        memberChatRoomRepository.save(MemberChatRoom.builder().member(member).chatRoom(chatRoom).build());
        List<Member> users = memberChatRoomRepository.findMembersByChatRoomId(Long.parseLong(roomId)).stream().map(MemberChatRoom::getMember).collect(Collectors.toList());

        messageSender.convertAndSend("/sub/chat/room/" + dto.getChatRoomId(), users);
    }

    @Transactional
    public void sendMessage(ChatMessageSocketDto dto) {
        String userId = dto.getChatUserId();
        String chatRoomId = dto.getChatRoomId();
        String msg = dto.getContent();
        ArrayList<String> memberLst = dto.getChatRoomMembers();
        ArrayList<String> chatImg = dto.getChatImages();
        Member sender = memberRepository.findById(Long.parseLong(userId)).orElseThrow(() -> new CustomException(MemberErrorCode.MEMBER_NOT_FOUND));

        // 보낼 채팅방 찾기
//        MemberChatRoom memberChatRoom = memberChatRoomRepository.findById(Long.parseLong(chatRoomId)).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 채팅방입니다."));
//        ChatRoom chatRoom = memberChatRoom.getChatRoom();
        ChatRoom chatRoom = chatRoomRepository.findChatRoomById(Long.parseLong(chatRoomId)).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 채팅방입니다."));
        chatRoom.updateLastMessage(msg);

        //메시지 저장
        ChatMessage chatMsg = ChatMessage.builder().sender(sender).content(msg).build();
        chatMessageRepository.save(chatMsg);

        //메시지 보내기
        messageSender.convertAndSend("/sub/chat/room/" + dto.getChatRoomId(), ChatMessageDto.Response.builder().roomId(dto.getChatRoomId()).content(msg).senderId(sender.getId()).build());



    }

    @Transactional
    public void userQuit(ChatRoomSocketDto dto) {
    }
}
