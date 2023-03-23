package com.jhd.dotime.tasks.mapper;

import com.jhd.dotime.members.dto.MemberRequestDto;
import com.jhd.dotime.members.entity.Member;
import com.jhd.dotime.members.mapper.MemberMapper;
import com.jhd.dotime.tasks.dto.TaskRequestDto;
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
        MemberRequestDto memberRequestDto = MemberRequestDto.builder()
                .email("Taskmapper@email.com")
                .password("1234")
                .username("mapperTest")
                .build();

        Member member = memberMapper.toEntity(memberRequestDto);

        TaskRequestDto taskRequestDto = new TaskRequestDto("new task", "new task content", "testTag");
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