package com.jhd.dotime.tasktime.service;

import com.jhd.dotime.members.repository.MemberRepository;
import com.jhd.dotime.tasks.common.error.TaskErrorCode;
import com.jhd.dotime.tasks.common.exception.TaskException;
import com.jhd.dotime.tasks.entity.Task;
import com.jhd.dotime.tasks.repository.TaskRepository;
import com.jhd.dotime.tasktime.common.error.TaskTimeLogErrorCode;
import com.jhd.dotime.tasktime.common.exception.TaskTimeLogException;
import com.jhd.dotime.tasktime.dto.TaskTimeLogDto;
import com.jhd.dotime.tasktime.entity.TaskTimeLog;
import com.jhd.dotime.tasktime.mapper.TaskTimeLogMapper;
import com.jhd.dotime.tasktime.repository.TaskTimeLogRepository;
import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TaskTimeLogServiceImpl implements TaskTimeLogService {

    private final TaskTimeLogMapper taskTimeLogMapper = Mappers.getMapper(TaskTimeLogMapper.class);

    private final TaskTimeLogRepository taskTimeLogRepository;

    private final TaskRepository taskRepository;

    @Override
    @Transactional
    public TaskTimeLogDto.Response startTimeLog(TaskTimeLogDto.Request requestDto) {
        Task task = taskRepository.findById(requestDto.getTaskId())
                .orElseThrow(() -> new TaskException(TaskErrorCode.TASK_NOT_FOUNT));

        return taskTimeLogMapper.toResponseDto(taskTimeLogRepository.save(taskTimeLogMapper.toEntity(requestDto, task)));
    }

    @Override
    @Transactional
    public TaskTimeLogDto.Response pauseTimeLog(TaskTimeLogDto.Request requestDto) {
        taskRepository.findById(requestDto.getTaskId())
                .orElseThrow(() -> new TaskException(TaskErrorCode.TASK_NOT_FOUNT));

        TaskTimeLog taskTimeLog = taskTimeLogRepository.findById(requestDto.getId())
                .orElseThrow(() -> new TaskTimeLogException(TaskTimeLogErrorCode.TASK_TIME_LOG_NOT_FOUNT));

        taskTimeLog.update(requestDto.getStartTime(), requestDto.getEndTime());

        return taskTimeLogMapper.toResponseDto(taskTimeLog);
    }

    @Override
    @Transactional
    public TaskTimeLogDto.Response endTimeLog(TaskTimeLogDto.Request requestDto) {
        taskRepository.findById(requestDto.getTaskId())
                .orElseThrow(() -> new TaskException(TaskErrorCode.TASK_NOT_FOUNT));

        TaskTimeLog taskTimeLog = taskTimeLogRepository.findById(requestDto.getId())
                .orElseThrow(() -> new TaskTimeLogException(TaskTimeLogErrorCode.TASK_TIME_LOG_NOT_FOUNT));

        taskTimeLog.update(requestDto.getStartTime(), requestDto.getEndTime());

        return taskTimeLogMapper.toResponseDto(taskTimeLog);
    }

    @Override
    @Transactional(readOnly = true)
    public List<TaskTimeLogDto.Response> getTimeLog(Long taskId) {
        taskRepository.findById(taskId)
                .orElseThrow(() -> new TaskException(TaskErrorCode.TASK_NOT_FOUNT));

        return taskTimeLogRepository.findByTaskId(taskId).stream()
                .map(taskTimeLogMapper::toResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<TaskTimeLogDto.Response> getTimeLogByMember(Long memberId) {
        List<Task> taskIdList = taskRepository.findTaskListByMemberId(memberId);
        List<TaskTimeLogDto.Response> responseList = new ArrayList<>();

        for(Task task : taskIdList){
            responseList.addAll(
                    taskTimeLogRepository.findByTaskId(task.getId())
                            .stream()
                            .map(taskTimeLogMapper::toResponseDto)
                            .collect(Collectors.toList())
            );
        }

        return responseList;
    }
}
