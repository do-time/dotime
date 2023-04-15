package com.jhd.dotime.tasktimelog.mapper;

import com.jhd.dotime.tasks.entity.Task;
import com.jhd.dotime.tasktimelog.dto.TaskTimeLogDto;
import com.jhd.dotime.tasktimelog.entity.TaskTimeLog;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import static org.junit.jupiter.api.Assertions.*;

class TaskTimeLogMapperTest {
    private TaskTimeLogMapper taskTimeLogMapper = Mappers.getMapper(TaskTimeLogMapper.class);

    @Test
    public void toEntityTest(){
        //given
        TaskTimeLogDto.Request request = new TaskTimeLogDto.Request(1L);
        Task task = Task.builder()
                .title("제목")
                .content("내용")
                .build();

        //when
        TaskTimeLog taskTimeLog = taskTimeLogMapper.toEntity(task);

        //then
        assertAll(
                () -> taskTimeLog.getTask().equals(task)
        );
    }
}