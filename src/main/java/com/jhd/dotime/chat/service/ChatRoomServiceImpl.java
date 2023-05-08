package com.jhd.dotime.chat.service;

import com.jhd.dotime.chat.common.error.ChatRoomErrorCode;
import com.jhd.dotime.chat.common.exception.ChatException;
import com.jhd.dotime.chat.dto.ChatRoomDto;
import com.jhd.dotime.chat.entity.ChatRoom;
import com.jhd.dotime.chat.repository.ChatRoomRepository;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Collections.reverse;

@Service
@RequiredArgsConstructor
public class ChatRoomServiceImpl implements ChatRoomService {
    private final ChatRoomRepository chatRoomRepository;

    @Override
    public List<ChatRoomDto.Response> createChatroom(ChatRoomDto.Request chatroomRequestDto) {
        ChatRoom chatRoom = chatRoomRepository.save(ChatRoom.builder()
                .roomname(chatroomRequestDto.getName())
                .build());
        System.out.println("chatRoom.getName() = " + chatRoom.getRoomname());
        return chatRoomRepository.findChatRoomById(chatRoom.getId()).stream().map(ChatRoomDto.Response::of).collect(Collectors.toList());
    }

    @Override
    public List<ChatRoomDto.Response> findAllRoom() {
        return chatRoomRepository.findAll().stream().map(ChatRoomDto.Response::of).collect(Collectors.collectingAndThen(
                Collectors.toList(),
                lst -> {
                    Collections.reverse(lst);
                    return lst;
                }));
    }

    /**
     * chat room 반환 by Room Id
     * @param roomId
     * @return
     */
    @Override
    public ChatRoomDto.Response findChatRoomById(String roomId) {
        return chatRoomRepository.findChatRoomById(Long.parseLong(roomId))
                .stream()
                .map(ChatRoomDto.Response::of)
                .collect(Collectors.toList()).get(0);
    }

    @Override
    public void deleteChatRoom(String roomId) {
        ChatRoom chatRoom = chatRoomRepository.findChatRoomById(Long.parseLong(roomId)).orElseThrow(() -> new ChatException(ChatRoomErrorCode.CHATROOM_NOT_FOUND));
        chatRoomRepository.delete(chatRoom);

    }


}
