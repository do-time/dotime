package com.jhd.dotime.members.service;

import com.jhd.dotime.members.dto.MemberDto;
import com.jhd.dotime.members.entity.Member;
import com.jhd.dotime.tasks.dto.TaskResponseDto;
import com.jhd.dotime.tasks.entity.Task;

import java.util.List;
import java.util.Optional;

public interface MemberService {
    void createMember(MemberDto memberDto);
//    void createMember(Member member);

    Optional<Member> getMember(String email);

    void updateMember(MemberDto memberDto);

    void updatePassword(String email, String password);

    void deleteMember(Long id);

    Optional<Member> getMember(Long memberId);
}
