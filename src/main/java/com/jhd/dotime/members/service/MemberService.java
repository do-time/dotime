package com.jhd.dotime.members.service;

import com.jhd.dotime.members.dto.MemberDto;
import com.jhd.dotime.members.dto.MemberResponseDto;
import com.jhd.dotime.members.entity.Member;
import com.jhd.dotime.tasks.dto.TaskResponseDto;
import com.jhd.dotime.tasks.entity.Task;

import java.util.List;
import java.util.Optional;

public interface MemberService {
    void createMember(MemberDto memberDto);
//    void createMember(Member member);

    MemberResponseDto getMember(String email);

    void updateMember(MemberDto memberDto);

    void updatePassword(MemberDto memberDto);

    void deleteMember(Long id);

    Member getMember(Long memberId);
}
