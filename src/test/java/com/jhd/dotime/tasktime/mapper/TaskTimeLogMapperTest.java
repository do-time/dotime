package com.jhd.dotime.tasktime.mapper;

import com.jhd.dotime.tasks.entity.Task;
import com.jhd.dotime.tasktime.dto.TaskTimeLogDto;
import com.jhd.dotime.tasktime.entity.TaskTimeLog;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class TaskTimeLogMapperTest {
    private TaskTimeLogMapper taskTimeLogMapper = Mappers.getMapper(TaskTimeLogMapper.class);

    @Test
    public void toEntityTest(){
        //given
        TaskTimeLogDto.Request requestDto = new TaskTimeLogDto.Request(null, 1L, LocalDateTime.now(), null);
        Task task = Task.builder()
                .title("제목")
                .content("내용")
                .build();

        //when
        TaskTimeLog taskTimeLog = taskTimeLogMapper.toEntity(requestDto, task);

        //then
        assertAll(
                () -> taskTimeLog.getTask().equals(task),
                () -> taskTimeLog.getStartTime().equals(requestDto.getStartTime())
        );
    }

    @Test
    public void toResponseDtoTest(){
        //given
        TaskTimeLogDto.Request requestDto = new TaskTimeLogDto.Request(1L, 1L, LocalDateTime.now(), LocalDateTime.now());
        Task task = Task.builder()
                .title("제목")
                .content("내용")
                .build();

        //when
        TaskTimeLog taskTimeLog = taskTimeLogMapper.toEntity(requestDto, task);

        TaskTimeLogDto.Response responseDto = taskTimeLogMapper.toResponseDto(taskTimeLog);

        //then
        assertAll(
                () -> responseDto.getStartTime().equals(taskTimeLog.getStartTime()),
                () -> responseDto.getEndTime().equals(taskTimeLog.getEndTime())
        );
    }
}