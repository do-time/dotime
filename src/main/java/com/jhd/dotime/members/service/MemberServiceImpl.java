package com.jhd.dotime.members.service;

import com.jhd.dotime.members.common.exception.NotFoundException;
import com.jhd.dotime.members.dto.MemberDto;
import com.jhd.dotime.members.entity.Member;
import com.jhd.dotime.members.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService{

    private final MemberRepository memberRepository;

    @Override
    public void createMember(MemberDto memberDto) {
//        Member member = Member.builder()
//                .email(memberDto.getEmail())
//                .password(memberDto.getPassword())
//                .username(memberDto.getUsername())
//                .profileImage(memberDto.getProfileImage())
//                .build();

        memberRepository.save(memberDto.toEntity());
    }

    @Override
    public Optional<Member> getMember(String email) {
        return memberRepository.findByEmail(email);
    }

    @Override
    public void updateMember(MemberDto memberDto) {
        getMember(memberDto.getEmail()).orElseThrow(() -> new NotFoundException("Member does not exist"));

        memberRepository.save(Member.builder()
                .email(memberDto.getEmail())
                .password(memberDto.getPassword())
                .username(memberDto.getUsername())
                .profileImage(memberDto.getProfileImage())
                .build());
    }

    @Override
    public void updatePassword(String email, String password) {
        getMember(email).orElseThrow(() -> new NotFoundException("Member does not exist"));

        memberRepository.save(Member.builder()
                .password(password)
                .build());
    }

    @Override
    public void deleteMember(String email) {
        getMember(email).orElseThrow(() -> new NotFoundException("Member does not exist"));

        memberRepository.deleteByEmail(email);
    }




}
