package com.jhd.dotime.members.service;

import com.jhd.dotime.members.dto.MemberDto;
import com.jhd.dotime.members.entity.Member;

public interface MemberService {
    void createMember(MemberDto.Request memberDtoRequest);


    MemberDto.Response getMember(String email);

    void updateMember(MemberDto.Request memberDtoRequest);

    void updatePassword(MemberDto.Request memberDtoRequest);

    void deleteMember(Long id);

    Member getMember(Long memberId);

    boolean duplicateCheckEmail(String email);
}
