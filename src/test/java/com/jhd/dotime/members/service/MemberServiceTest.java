package com.jhd.dotime.members.service;

import com.jhd.dotime.members.common.exception.NotFoundException;
import com.jhd.dotime.members.dto.MemberDto;
import com.jhd.dotime.members.dto.MemberDto.Request;
import com.jhd.dotime.members.entity.Member;
import com.jhd.dotime.members.repository.MemberRepository;
import com.jhd.dotime.tasks.repository.TaskRepository;
import com.jhd.dotime.tasks.service.TaskServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;


@ExtendWith(MockitoExtension.class)
class MemberServiceTest {

    @InjectMocks
    private MemberServiceImpl memberService;

    @Mock
    private MemberRepository memberRepository;

    @InjectMocks
    private TaskServiceImpl taskService;

    @Mock
    private TaskRepository taskRepository;

    @Test
    @DisplayName("Member 회원 가입 성공 테스트")
    public void createMember() {
        //given
        MemberDto.Request newMember = new MemberDto.Request("test@test.com", "testMan", "1234", "");

        Member member = Member.builder()
                .email("test@test.com")
                .password("1234")
                .username("testMan")
                .profileImage("")
                .build();

        Long fakeMemberId = 1l;
        ReflectionTestUtils.setField(member, "id", fakeMemberId);

        given(memberRepository.save(any(Member.class))).willReturn(member);
        given(memberRepository.findByEmail(member.getEmail())).willReturn(Optional.of(member));

        //when
        memberService.createMember(newMember);
        Member findMember = memberRepository.findByEmail(member.getEmail()).orElseThrow(() -> new NotFoundException("Member does not exist"));

        //then
        Assertions.assertThat(findMember.getEmail()).isEqualTo(newMember.getEmail());
        assertAll(
                () -> findMember.getEmail().equals(newMember.getEmail()),
                () -> findMember.getEmail().equals(newMember.getEmail())
        );
    }

    @Test
    void getMember() {
        //given
        //when
        //then
    }

    @Test
    void updateMember() {
        //given
        //when
        //then
    }

    @Test
    void updatePassword() {
        //given
        //when
        //then
    }

    @Test
    void deleteMember() {
        //given
        //when
        //then
    }
}