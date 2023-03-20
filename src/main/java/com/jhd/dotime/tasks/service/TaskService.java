package com.jhd.dotime.tasks.service;

import com.jhd.dotime.tasks.dto.TaskRequestDto;
import com.jhd.dotime.tasks.dto.TaskResponseDto;
import com.jhd.dotime.tasks.entity.Task;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TaskService {

    Long insert(Long memberId, TaskRequestDto taskRequestDto);

    TaskResponseDto findTask(Long id);


    List<TaskResponseDto> findTaskList();

    Long delete(Long id);

    void save(Task task);

    Long update(Long id, TaskRequestDto taskRequestDto);

    List<TaskResponseDto> getTaskListByMemberId(Long memberId);


}
