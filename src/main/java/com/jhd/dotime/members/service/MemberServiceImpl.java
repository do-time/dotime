package com.jhd.dotime.members.service;

import com.jhd.dotime.members.dto.MemberDto;
import com.jhd.dotime.members.entity.Member;
import com.jhd.dotime.members.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService{

    private final MemberRepository memberRepository;

    @Override
    public void createMember(MemberDto memberDto) {
        memberRepository.createMember(memberDto);
    }

    @Override
    public Member getMember(String email) {
        return memberRepository.getMember(email);
    }

    @Override
    public void updateMember(MemberDto memberDto) {
        memberRepository.updateMember(memberDto);
    }

    @Override
    public void updatePassword(String email, String password) {
        memberRepository.updatePassword(email, password);
    }

    @Override
    public void deleteMember(String email) {
        memberRepository.deleteMember(email);
    }
}
