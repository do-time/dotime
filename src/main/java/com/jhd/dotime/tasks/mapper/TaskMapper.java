package com.jhd.dotime.tasks.mapper;

import com.jhd.dotime.members.entity.Member;
import com.jhd.dotime.tasks.dto.TaskRequestDto;
import com.jhd.dotime.tasks.entity.Task;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR, // 생성자 주입 전략
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface TaskMapper {

    default Task toEntity(TaskRequestDto taskRequestDto, Member member){
        return Task.builder()
                .member(member)
                .title(taskRequestDto.getTitle())
                .content(taskRequestDto.getContent())
                .build();
    }
}