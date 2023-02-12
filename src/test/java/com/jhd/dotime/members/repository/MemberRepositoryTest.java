package com.jhd.dotime.members.repository;

import com.jhd.dotime.members.common.exception.NotFoundException;
import com.jhd.dotime.members.entity.Member;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;

@DataJpaTest
@RunWith(SpringRunner.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class MemberRepositoryTest {
    @Autowired
    private MemberRepository memberRepository;

    @BeforeEach
    public void beforeEach(){
        memberRepository.deleteAll();
    }

    @Test
    @DisplayName("멤버 저장")
    public void createMember() {
        // given
        Member member = Member.builder()
                .email("test@test.com")
                .password("1234")
                .username("testMan")
                .profileImage("")
                .createdDate(LocalDateTime.now())
                .updatedDate(LocalDateTime.now())
                .build();
        // when
        Member savedMember = memberRepository.save(member);

        // then
        Assertions.assertThat(member).isSameAs(savedMember);
        Assertions.assertThat(savedMember.getId()).isNotNull();
        Assertions.assertThat(member.getEmail()).isEqualTo(savedMember.getEmail());
        Assertions.assertThat(memberRepository.count()).isEqualTo(1);
        System.out.println(savedMember.toString());
    }

    @Test
    @DisplayName("멤버 조회")
    public void findMember() {
        // given
        Member newMember = Member.builder()
                .email("test@test.com")
                .password("1234")
                .username("testMan")
                .profileImage("")
                .build();
        memberRepository.save(newMember);
        // when
        Member findMember = memberRepository.findByEmail("test@test.com").orElseThrow(() -> new NotFoundException("Member does not exist"));

        // then
        Assertions.assertThat(findMember.getEmail().equals(newMember.getEmail()));
        Assertions.assertThat(findMember.getPassword().equals(newMember.getPassword()));
        Assertions.assertThat(findMember.getUsername().equals(newMember.getUsername()));
        Assertions.assertThat(memberRepository.count()).isEqualTo(1);
    }
}