package com.jhd.dotime.tasks.mapper;

import com.jhd.dotime.members.dto.MemberDto;
import com.jhd.dotime.members.dto.MemberDto.Request;
import com.jhd.dotime.members.entity.Member;
import com.jhd.dotime.members.mapper.MemberMapper;

import com.jhd.dotime.tasks.dto.TaskDto;
import com.jhd.dotime.tasks.entity.Task;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import static org.junit.jupiter.api.Assertions.*;

class TaskMapperTest {
    private MemberMapper memberMapper = Mappers.getMapper(MemberMapper.class);
    private TaskMapper taskMapper = Mappers.getMapper(TaskMapper.class);

    @Test
    public void toEntityTest(){
        //given
        MemberDto.Request memberDtoRequest = MemberDto.Request.builder()
                .email("Taskmapper@email.com")
                .password("1234")
                .username("mapperTest")
                .build();

        Member member = memberMapper.toEntity(memberDtoRequest);

        TaskDto.Request taskRequestDto = new TaskDto.Request("new task", "new task content", "testTag");
        //when

        Task task = taskMapper.toEntity(taskRequestDto, member);

        //then
        assertAll(
                () -> assertTrue(task.getTitle().equals(taskRequestDto.getTitle())),
                () -> assertTrue(task.getContent().equals(taskRequestDto.getContent())),
                () -> assertTrue(task.getMember().equals(member))
        );
    }

}