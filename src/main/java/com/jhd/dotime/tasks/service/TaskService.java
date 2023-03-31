package com.jhd.dotime.tasks.service;

import com.jhd.dotime.tasks.dto.TaskDto;


import com.jhd.dotime.tasks.entity.Task;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TaskService {

    Long insert(Long memberId, TaskDto.Request taskRequestDto);

    List<TaskDto.Response> findTask(Long id);


    List<TaskDto.Response> findTaskList();

    Long delete(Long id);

    void save(Task task);

    Long update(Long id, TaskDto.Request taskRequestDto);

    List<TaskDto.Response> getTaskListByMemberId(Long memberId);


}