package com.jhd.dotime.members.service;

import com.jhd.dotime.common.exception.CustomException;
import com.jhd.dotime.members.common.error.MemberErrorCode;
import com.jhd.dotime.members.dto.MemberDto;
import com.jhd.dotime.members.dto.MemberDto.Request;
import com.jhd.dotime.members.dto.MemberDto.Response;
import com.jhd.dotime.members.entity.Member;
import com.jhd.dotime.members.mapper.MemberMapper;
import com.jhd.dotime.members.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;

    private final MemberMapper memberMapper = Mappers.getMapper(MemberMapper.class);

    //private final MemberMapper memberMapper = Mappers.getMapper(MemberMapper.class);

    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public void createMember(MemberDto.Request memberDtoRequest) {
        duplicateCheckEmail(memberDtoRequest.getEmail());

        Member member = memberMapper.toEntity(memberDtoRequest);

        memberRepository.save(member);
    }

    @Override
    @Transactional(readOnly = true)
    public MemberDto.Response getMember(String email) {
        return memberRepository.findByEmail(email)
                .map(MemberDto.Response::new)
                .orElseThrow(() -> new CustomException(MemberErrorCode.MEMBER_NOT_FOUND));
    }

    @Override
    @Transactional(readOnly = true)
    public Member getMember(Long memberId) {
        return memberRepository.findById(memberId).orElseThrow(() -> new CustomException(MemberErrorCode.MEMBER_NOT_FOUND));
    }

    @Override
    @Transactional
    public void updateMember(MemberDto.Request memberDtoRequest) {
        Member member = getMember(memberDtoRequest.getId());

        member.updateInfo(memberDtoRequest.getUsername());
    }

    /*
     * Spring Security 적용 이후 개발 예정
     */
    @Override
    @Transactional
    public void updatePassword(MemberDto.Request memberDtoRequest) {
        getMember(memberDtoRequest.getPassword());

        memberRepository.save(Member.builder().password(memberDtoRequest.getPassword()).build());
    }

    /*
     * delete의 경우 삭제 컬럼을 하나 만들어 삭제 여부 표시하면 좋을 듯(개인정보 데이터도 함께 날리고)
     * 추후 사용자 데이터 사용을 위해
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
