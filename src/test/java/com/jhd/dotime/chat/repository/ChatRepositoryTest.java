package com.jhd.dotime.chat.repository;

import com.jhd.dotime.chat.entity.Chat;
import com.jhd.dotime.members.entity.Member;
import com.jhd.dotime.members.repository.MemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@ActiveProfiles("test")
class ChatRepositoryTest {

    Member sender;
    Member receiver;

    @Autowired
    private static ChatRepository chatRepository;

    @Autowired
    private static MemberRepository memberRepository;


    @BeforeEach
    void setUp() {
        sender = Member.builder()
                .username("member1")
                .email("test1@email.com")
                .password("1234")
                .profileImage("")
                .build();

        receiver = Member.builder()
                .username("member2")
                .email("test2@email.com")
                .password("1234")
                .profileImage("")
                .build();
    }

//    @AfterEach
//    void tearDown() {
////        chatRepository.deleteAll();
////        memberRepository.deleteAll();
//    }

    @Test
    @DisplayName("메시지 생성")
    void createMessage(){
        //given
        Member se = memberRepository.save(sender);
        Member rev = memberRepository.save(receiver);

        Chat newChat = Chat.builder()
                .content("hello world")
                .receiver(se)
                .sender(rev)
                .build();

        //when

        chatRepository.save(newChat);

        //then
        List<Chat> messageList = chatRepository.findAll();
        Assertions.assertThat(newChat.getId()).isEqualTo(messageList.get(0).getId());

    }
}