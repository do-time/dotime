package com.jhd.dotime.members.service;

import com.jhd.dotime.members.common.exception.NotFoundException;
import com.jhd.dotime.members.dto.MemberDto;
import com.jhd.dotime.members.entity.Member;
import com.jhd.dotime.members.repository.MemberRepository;
import com.jhd.dotime.tasks.dto.TaskResponseDto;
import com.jhd.dotime.tasks.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService{

    private final MemberRepository memberRepository;

    private final TaskRepository taskRepository;

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

//        memberRepository.save(Member.builder()
//                .email(memberDto.getEmail())
//                .password(memberDto.getPassword())
//                .username(memberDto.getUsername())
//                .profileImage(memberDto.getProfileImage())
//                .build());
        memberRepository.save(memberDto.toEntity());
    }

    /*
     update password 같은 경우는 Member Entity 내부에 작성해도 좋은거 같습니다.
     Task 참조
     */
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
