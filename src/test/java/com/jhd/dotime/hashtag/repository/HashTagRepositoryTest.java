package com.jhd.dotime.hashtag.repository;


import com.jhd.dotime.hashtag.entity.HashTag;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
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
    @DisplayName("tag 생성 테스트")
    public void tagCreate() {
        //given
        HashTag newTag = HashTag.builder().id(1L).name("newTag").description("this is new tag").build();

        //when
        hashTagRepository.save(newTag);

        List<HashTag> findTag = hashTagRepository.findAll();

        //then
        Assertions.assertThat(newTag.getId()).isEqualTo(findTag.get(0).getId());

    }
}