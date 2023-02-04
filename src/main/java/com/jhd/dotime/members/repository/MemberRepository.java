package com.jhd.dotime.members.repository;

import com.jhd.dotime.members.dto.MemberDto;
import com.jhd.dotime.members.entity.Member;

public interface MemberRepository {

    void createMember(MemberDto memberDto);

    Member getMember(String email);

    void updateMember(MemberDto memberDto);

    void updatePassword(String email, String password);

    void deleteMember(String email);
}
