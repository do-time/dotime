package com.jhd.dotime.chat.service;

import com.jhd.dotime.chat.entity.MemberChatRoom;
import com.jhd.dotime.chat.repository.ChatRoomRepository;
import com.jhd.dotime.chat.repository.MemberChatRoomRepository;
import com.jhd.dotime.members.entity.Member;
import com.jhd.dotime.members.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MemberChatRoomServiceImpl implements MemberChatRoomService{

    private final MemberRepository memberRepository;
    private final MemberChatRoomRepository memberChatRoomRepository;
    private final ChatRoomRepository chatRoomRepository;

    @Override
    public List<Member> findAllMemberByRoomId(Long roomId) {
        return memberChatRoomRepository.findMembersByChatRoomId(roomId).stream().map(MemberChatRoom::getMember).collect(Collectors.toList());
    }
}
