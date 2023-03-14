package com.jhd.dotime.members.service;

import com.jhd.dotime.auth.dto.LoginResponseDto;
import com.jhd.dotime.members.dto.MemberRequestDto;
import com.jhd.dotime.members.dto.MemberResponseDto;
import com.jhd.dotime.members.entity.Member;

public interface MemberService {
    void createMember(MemberRequestDto memberRequestDto);


    MemberResponseDto getMember(String email);

    void updateMember(MemberRequestDto memberRequestDto);

    void updatePassword(MemberRequestDto memberRequestDto);

    void deleteMember(Long id);

    Member getMember(Long memberId);

    boolean duplicateCheckEmail(String email);
}
