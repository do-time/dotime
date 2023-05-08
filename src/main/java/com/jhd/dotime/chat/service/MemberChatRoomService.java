package com.jhd.dotime.chat.service;

import com.jhd.dotime.members.entity.Member;

import java.util.List;

public interface MemberChatRoomService {

    List<Member> findAllMemberByRoomId(Long roomId);
}
