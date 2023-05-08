package com.jhd.dotime.chat.service;

import com.jhd.dotime.chat.common.error.ChatRoomErrorCode;
import com.jhd.dotime.chat.common.exception.ChatException;
import com.jhd.dotime.chat.dto.ChatRoomDto;
import com.jhd.dotime.chat.entity.ChatRoom;
import com.jhd.dotime.chat.repository.ChatMessageRepository;
import com.jhd.dotime.chat.repository.ChatRoomRepository;
import com.jhd.dotime.members.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ChatServiceImpl implements ChatService{

    private final ChatRoomRepository chatRoomRepository;
    private final ChatMessageRepository chatMessageRepository;
    private final MemberRepository memberRepository;

    /**
     * 추후에 hash tag로 room을 검색할 수 있게 개발해야 함.
     * @param roomId
     * @return
     */
    @Override
    public ChatRoom findRoom(String roomId) {
        ChatRoom chatRoom = chatRoomRepository.findById(roomId).orElseThrow(() -> new ChatException(ChatRoomErrorCode.CHATROOM_NOT_FOUND));

        return chatRoom;
    }

    @Override
    public ChatRoom createRoom(ChatRoomDto.Request request) {
        //초대할 유저 목록
        //chat room 생성
        //각 유저별로 채팅방이 생성되어야 함.
        return null;
    }

    /**
     *
     * @param chatRoomRequest
     * @return
     */
    @Override
    public ChatRoomDto.Response enterChatRoom(ChatRoomDto.Request chatRoomRequest) {
        // room을 create 한 뒤에 이용할 로직
        return null;
    }

    @Override
    public void leaveChatRoom(String roomId) {

    }


}
