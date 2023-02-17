package com.jhd.dotime.members.service;

import com.jhd.dotime.members.common.exception.NotFoundException;
import com.jhd.dotime.members.dto.MemberDto;
import com.jhd.dotime.members.entity.Member;
import com.jhd.dotime.members.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;

    @Override
    @Transactional
    public void createMember(MemberDto memberDto) {
        memberRepository.save(memberDto.toEntity());
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Member> getMember(String email) {
        return memberRepository.findByEmail(email);
    }

    @Override
    public void updateMember(MemberDto memberDto) {
        Member member = getMember(memberDto.getEmail()).orElseThrow(() -> new NotFoundException("Member does not exist"));

        member.updateInfo(memberDto.getUsername());
    }


    /*
     * Spring Security 적용 이후 개발 예정
     */
    @Override
    @Transactional
    public void updatePassword(String email, String password) {
        getMember(email).orElseThrow(() -> new NotFoundException("Member does not exist"));

        memberRepository.save(Member.builder().password(password).build());
    }

    /*
     * delete의 경우 삭제 컬럼을 하나 만들어 삭제 여부 표시하면 좋을 듯(개인정보 데이터도 함께 날리고)
     * 추후 사용자 데이터 사용을 위해
     */
    @Override
    @Transactional
    public void deleteMember(String email) {
        memberRepository.delete(getMember(email).orElseThrow(() -> new NotFoundException("Member does not exist")));
    }

    @Override
    public Optional<Member> getMember(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
