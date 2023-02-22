package com.jhd.dotime.members.repository;

import com.jhd.dotime.members.common.exception.NotFoundException;
import com.jhd.dotime.members.entity.Member;
import com.jhd.dotime.tasks.entity.Task;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.ArrayList;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@MockBean(JpaMetamodelMappingContext.class)
public class MemberRepositoryTest {
    @Autowired
    private MemberRepository memberRepository;

    @BeforeAll
    public static void beforeAll(){
        member = Member.builder()
                .email("test@test.com")
                .password("1234")
                .username("testMan")
                .profileImage("")
                .build();
    }

    @AfterEach
    public void afterEach(){
        memberRepository.deleteAllInBatch();
    }

    static Member member;

    @Test
    @DisplayName("멤버 저장")
    public void createMember() {
        // given
        long count = memberRepository.count();
        // when
        Member savedMember = memberRepository.save(member);

        // then
        Assertions.assertThat(member).isSameAs(savedMember);
        Assertions.assertThat(savedMember.getId()).isNotNull();
        Assertions.assertThat(member.getEmail()).isEqualTo(savedMember.getEmail());
        Assertions.assertThat(memberRepository.count()).isEqualTo(count+1);
    }

    @Test
    @DisplayName("멤버 조회")
    public void findMember() {
        // given
        Member member = Member.builder()
                .email("test@test.com")
                .password("1234")
                .username("testMan")
                .profileImage("")
                .build();

        memberRepository.save(member);
        // when
        Member findMember = memberRepository.findByEmail("test@test.com").orElseThrow(() -> new NotFoundException("Member does not exist"));

        // then
        Assertions.assertThat(findMember.getEmail().equals(member.getEmail()));
        Assertions.assertThat(findMember.getPassword().equals(member.getPassword()));
        Assertions.assertThat(findMember.getUsername().equals(member.getUsername()));
        //Assertions.assertThat(memberRepository.count()).isEqualTo(1);
    }

    @Test
    @DisplayName("멤버 정보 변경")
    public void updateMember() {
        // given
        Member member = Member.builder()
                .email("test@test.com")
                .password("1234")
                .username("testMan")
                .profileImage("")
                .build();
        memberRepository.save(member);

        member.updateInfo("update name");

        // when
        Member findMember = memberRepository.findByEmail("test@test.com").orElseThrow(() -> new NotFoundException("Member does not exist"));

        // then
        Assertions.assertThat(findMember.getUsername().equals(member.getUsername()));
        Assertions.assertThat(memberRepository.count()).isEqualTo(1);
    }

    @Test
    @DisplayName("멤버 삭제")
    public void deleteMember() {
        // given
        long count = memberRepository.count();

        memberRepository.save(member);

        // when
        memberRepository.delete(member);

        // then
        Assertions.assertThat(memberRepository.count()).isEqualTo(count-1);
        Assertions.assertThatThrownBy(() -> {
            memberRepository.findByEmail("test@test.com").orElseThrow(() -> new NotFoundException("Member does not exist"));
        }).withFailMessage("Member does not exist");
    }
}