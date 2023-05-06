package com.jhd.dotime.tasktime.service;

import com.jhd.dotime.tasktime.dto.TaskTimeLogDto;

import java.util.List;

public interface TaskTimeLogService {
    TaskTimeLogDto.Response startTimeLog(TaskTimeLogDto.Request requestDto);

    TaskTimeLogDto.Response pauseTimeLog(TaskTimeLogDto.Request requestDto);

    TaskTimeLogDto.Response endTimeLog(TaskTimeLogDto.Request requestDto);

    List<TaskTimeLogDto.Response> getTimeLog(Long taskId);

    List<TaskTimeLogDto.Response> getTimeLogByMember(Long memberId);
}
