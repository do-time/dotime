package com.jhd.dotime.tasktimelog.service;

import com.jhd.dotime.tasktimelog.dto.TaskTimeLogDto;

public interface TaskTimeLogService {
    Long startTimeLog(TaskTimeLogDto.Request requestDto);
}
