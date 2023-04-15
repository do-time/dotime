package com.jhd.dotime.tasktimelog.mapper;

import com.jhd.dotime.tasks.entity.Task;
import com.jhd.dotime.tasktimelog.entity.TaskTimeLog;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR, // 생성자 주입 전략
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface TaskTimeLogMapper {
    default TaskTimeLog toEntity(Task task){
        return TaskTimeLog.builder()
                .task(task)
                .build();
    }
}
