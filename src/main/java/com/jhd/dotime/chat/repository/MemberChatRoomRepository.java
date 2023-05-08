package com.jhd.dotime.chat.repository;

import com.jhd.dotime.chat.entity.MemberChatRoom;
import com.jhd.dotime.members.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MemberChatRoomRepository extends JpaRepository<MemberChatRoom, Long> {

    List<Member> findMembersByChatRoomId(Long roomId);
}
