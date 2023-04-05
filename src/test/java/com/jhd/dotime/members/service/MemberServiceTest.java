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

import java.time.LocalDateTime;

import static org.mockito.ArgumentMatchers.any;


@ExtendWith(MockitoExtension.class)
class MemberServiceTest {

//    @InjectMocks
    private MemberServiceImpl memberService;

    @Mock

    private MemberRepository memberRepository;

    @InjectMocks
    private TaskServiceImpl taskService;

    @Mock
    private TaskRepository taskRepository;



    /*
     *
     * create 테스트에서 find를 쓰는게 맞을까?
     * UI/UX에서 회원가입한 회원에게 회원정보를 보여주거나 하는 과정이 있으면 member return해도 될듯
     *
     */
    @Test
    @DisplayName("Member 회원 가입 성공 테스트")
    public void createMember() {
        //given
        LocalDateTime now = LocalDateTime.now();
        MemberDto.Request newMember = new MemberDto.Request("test@test.com", "testMan", "1234", "");
        Member member = Member.builder()
                .email("11111@test.com")
                .password("1234")
                .username("testMan")
                .profileImage("")
//                .createdDate(now)
//                .updatedDate(now)
                .build();

//        Long fakeMemberId = 1l;
//        ReflectionTestUtils.setField(member, "id", fakeMemberId);

//        System.out.println(newMember.getEmail().equals(newmember.getEmail()));
//        System.out.println(member.toString());

//        given(memberRepository.save(any())).willReturn(member);

        //when
        memberService.createMember(newMember);
//        Member findMember = memberRepository.findByEmail(member.getEmail()).orElseThrow(() -> new NotFoundException("Member does not exist"));
        Member findMember = memberRepository.findByEmail(member.getEmail()).orElseThrow(() -> new NotFoundException("Member does not exist"));
//        System.out.println(findMember.toString());


//        Member findMember = memberRepository.findById(1l).orElseThrow();
        //then
        Assertions.assertThat(findMember).isNotNull();
//        Assertions.assertThat(findMember.getEmail()).isEqualTo(newMember.getEmail());
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