package com.jhd.dotime.tasks.service;

import com.jhd.dotime.members.entity.Member;
import com.jhd.dotime.tasks.dto.TaskSaveRequestDto;
import com.jhd.dotime.tasks.dto.TaskResponseDto;
import com.jhd.dotime.tasks.dto.TaskUpdateRequestDto;
import com.jhd.dotime.tasks.entity.Task;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TaskService {

    Long insert(Long memberId, TaskSaveRequestDto taskSaveRequestDto);

    TaskResponseDto findTask(Long id);


    List<TaskResponseDto> findTaskList();

    Long delete(Long id);

    void save(Task task);

    Long update(Long id, TaskUpdateRequestDto taskUpdateRequestDto);

    List<TaskResponseDto> getTaskListByMemberId(Long memberId);


}
