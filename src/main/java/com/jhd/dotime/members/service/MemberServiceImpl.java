package com.jhd.dotime.members.service;

import com.jhd.dotime.auth.dto.LoginResponseDto;
import com.jhd.dotime.common.exception.CustomException;
import com.jhd.dotime.members.common.error.MemberErrorCode;
import com.jhd.dotime.members.common.exception.NotFoundException;
import com.jhd.dotime.members.dto.MemberRequestDto;
import com.jhd.dotime.members.dto.MemberResponseDto;
import com.jhd.dotime.members.entity.Member;
import com.jhd.dotime.members.mapper.MemberDtoMapper;
import com.jhd.dotime.members.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.mapstruct.factory.Mappers;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;

    private final MemberDtoMapper dtoMapper = Mappers.getMapper(MemberDtoMapper.class);

    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public void createMember(MemberRequestDto memberRequestDto) {
        duplicateCheckEmail(memberRequestDto.getEmail());

        Member member = dtoMapper.toEntity(memberRequestDto);

        memberRepository.save(member);
    }

    @Override
    @Transactional(readOnly = true)
    public MemberResponseDto getMember(String email) {
        return memberRepository.findByEmail(email)
                .map(MemberResponseDto::new)
                .orElseThrow(() -> new CustomException(MemberErrorCode.MEMBER_NOT_FOUND));
    }

    @Override
    @Transactional(readOnly = true)
    public Member getMember(Long memberId) {
        return memberRepository.findById(memberId).orElseThrow(() -> new CustomException(MemberErrorCode.MEMBER_NOT_FOUND));
    }

    @Override
    @Transactional
    public void updateMember(MemberRequestDto memberRequestDto) {
        Member member = getMember(memberRequestDto.getId());

        member.updateInfo(memberRequestDto.getUsername());
    }

    /*
     * Spring Security ?????? ?????? ?????? ??????
     */
    @Override
    @Transactional
    public void updatePassword(MemberRequestDto memberRequestDto) {
        getMember(memberRequestDto.getPassword());

        memberRepository.save(Member.builder().password(memberRequestDto.getPassword()).build());
    }

    /*
     * delete??? ?????? ?????? ????????? ?????? ????????? ?????? ?????? ???????????? ?????? ???(???????????? ???????????? ?????? ?????????)
     * ?????? ????????? ????????? ????????? ??????
     */
    @Override
    @Transactional
    public void deleteMember(Long id) {
        memberRepository.delete(getMember(id));
    }

    @Override
    @Transactional(readOnly = true)
    public boolean duplicateCheckEmail(String email) {
        if (memberRepository.existsByEmail(email))
            throw new CustomException(MemberErrorCode.DUPLICATE_EMAIL);
        else {
            return true;
        }
    }
}
