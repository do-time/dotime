package com.jhd.dotime.members.entity;

import com.jhd.dotime.common.exception.CustomException;
import com.jhd.dotime.members.common.error.MemberErrorCode;
import com.jhd.dotime.members.dto.MemberRequestDto;
import com.jhd.dotime.members.mapper.MemberMapper;
import com.jhd.dotime.members.repository.MemberRepository;
import com.jhd.dotime.members.service.MemberService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

class MemberTest {
    @Test
    public  void equalsTest(){
        //given
        Map<Member, String> map= new HashMap<>();
        List<Member> list = new ArrayList<>();
        Member member1 = new Member(1L, "entity@Email.com", "entity","1234","",false,null);
        Member member2 = new Member(1L, "entity@Email.com", "entity","1234","",false,null);

        //when
        map.put(member1, "member1");
        map.put(member2, "member2");
        list.add(member1);
        list.add(member2);

        //then
        assertAll(
                () -> assertTrue(member1.equals(member2)),
                () -> assertTrue(map.size() == 1),
                () -> assertTrue(list.contains(member1)),
                () -> assertTrue(list.contains(member2))
        );
    }
}