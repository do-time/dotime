package com.jhd.dotime.tasktime.mapper;

import com.jhd.dotime.tasks.entity.Task;
import com.jhd.dotime.tasktime.dto.TaskTimeLogDto;
import com.jhd.dotime.tasktime.entity.TaskTimeLog;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR, // 생성자 주입 전략
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface TaskTimeLogMapper {
    default TaskTimeLog toEntity(TaskTimeLogDto.Request requestDto, Task task){
        return TaskTimeLog.builder()
                .task(task)
                .startTime(requestDto.getStartTime())
                .endTime(requestDto.getEndTime())
                .build();
    }

    default TaskTimeLogDto.Response toResponseDto(TaskTimeLog taskTimelog){
        return TaskTimeLogDto.Response.builder()
                .id(taskTimelog.getId())
                .taskId(taskTimelog.getTask().getId())
                .startTime(taskTimelog.getStartTime())
                .endTime(taskTimelog.getEndTime())
                .build();
    }
}
