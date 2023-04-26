package com.jhd.dotime.tasktime.mapper;

import com.jhd.dotime.tasks.entity.Task;
import com.jhd.dotime.tasktime.dto.AllocationTimeDto;
import com.jhd.dotime.tasktime.dto.TaskTimeLogDto;
import com.jhd.dotime.tasktime.entity.AllocationTime;
import com.jhd.dotime.tasktime.entity.TaskTimeLog;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR, // 생성자 주입 전략
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface AllocationTimeMapper {
    default AllocationTime toEntity(String type, Long hour , Task task){
        return AllocationTime.builder()
                .category(type)
                .hour(hour)
                .task(task)
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
