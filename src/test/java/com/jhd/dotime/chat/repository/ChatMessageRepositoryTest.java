package com.jhd.dotime.chat.repository;

import com.jhd.dotime.chat.entity.ChatMessage;
import com.jhd.dotime.members.entity.Member;
import com.jhd.dotime.members.repository.MemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;


@SpringBootTest
@ActiveProfiles("test")
class ChatMessageRepositoryTest {


    @Autowired
    private ChatRepository chatRepository;

    @Autowired
    private MemberRepository memberRepository;


    @Test
    @DisplayName("메시지 생성")
    void createMessage(){
        Member sender = Member.builder()
                .username("member1")
                .email("test1@email.com")
                .password("1234")
                .profileImage("")
                .build();

        Member receiver = Member.builder()
                .username("member2")
                .email("test2@email.com")
                .password("1234")
                .profileImage("")
                .build();

        //given

        memberRepository.save(sender);
//        memberRepository.save(receiver);

        ChatMessage newChat = ChatMessage.builder()
                .content("hello world")
//                .receiver(receiver)
                .sender(sender)
                .build();

        //when
        chatRepository.save(newChat);

        //then
        List<ChatMessage> messageList = chatRepository.findAll();
        Assertions.assertThat(newChat.getId()).isEqualTo(messageList.get(0).getId());

    }
}