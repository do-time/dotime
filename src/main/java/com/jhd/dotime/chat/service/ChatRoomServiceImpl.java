package com.jhd.dotime.chat.service;

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
    @Autowired
    private final ChatRoomRepository chatRoomRepository;

    @Override
    public List<ChatRoomDto.Response> createChatroom(ChatRoomDto.Request chatroomRequestDto) {
        ChatRoom chatRoom = chatRoomRepository.save(ChatRoom
                .builder()
                .name(chatroomRequestDto.getName())
                .build());
        return null;

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
}
