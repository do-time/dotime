package com.jhd.dotime.hashtag.repository;


import com.jhd.dotime.DotimeApplication;
import com.jhd.dotime.hashtag.entity.HashTag;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;

import java.util.List;



@ActiveProfiles("test") // application-test
@SpringBootTest
public class HashTagRepositoryTest {

    @Autowired
    private HashTagRepository hashTagRepository;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    @Order(1)
    @DisplayName("tag 생성 테스트")
    public void tagCreate() {
        //given
        HashTag newTag = HashTag.builder().id(1L).name("newTag").descr("this is new tag").build();

        //when
        hashTagRepository.save(newTag);

        List<HashTag> findTag = hashTagRepository.findAll();

        //then
        Assertions.assertThat(newTag.getId()).isEqualTo(findTag.get(0).getId());

    }

    @Test
    @Order(2)
    @DisplayName("tag 삭제 테스트")
    public void tagDelete(){
        //given
        HashTag newTag = HashTag.builder().id(1L).name("newTag").descr("this is new tag").build();

        //when
        hashTagRepository.save(newTag);
        List<HashTag> findTag = hashTagRepository.findAll();
        System.out.println("size: " + findTag.size());
        Assertions.assertThat(findTag.size()).isEqualTo(1);

        //then
        hashTagRepository.delete(newTag);
        List<HashTag> deleteTag = hashTagRepository.findAll();
        Assertions.assertThat(deleteTag).isNull();

    }
}