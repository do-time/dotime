package com.jhd.dotime.tasktimelog.service;

import com.jhd.dotime.tasks.common.error.TaskErrorCode;
import com.jhd.dotime.tasks.common.exception.TaskException;
import com.jhd.dotime.tasks.entity.Task;
import com.jhd.dotime.tasks.repository.TaskRepository;
import com.jhd.dotime.tasktimelog.dto.TaskTimeLogDto;
import com.jhd.dotime.tasktimelog.mapper.TaskTimeLogMapper;
import com.jhd.dotime.tasktimelog.repository.TaskTimeLogRepository;
import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TaskTimeLogServiceImpl implements TaskTimeLogService {

    private TaskTimeLogMapper taskTimeLogMapper = Mappers.getMapper(TaskTimeLogMapper.class);

    private TaskTimeLogRepository taskTimeLogRepository;

    private TaskRepository taskRepository;

    @Override
    public Long startTimeLog(TaskTimeLogDto.Request requestDto) {
        Task task = taskRepository.findById(requestDto.getId())
                .orElseThrow(() -> new TaskException(TaskErrorCode.TASK_NOT_FOUNT));

        taskTimeLogRepository.save(taskTimeLogMapper.toEntity(task));
        return null;
    }
}
