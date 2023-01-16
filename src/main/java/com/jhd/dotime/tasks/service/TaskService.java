package com.jhd.dotime.tasks.service;

import com.jhd.dotime.tasks.entity.Task;

import java.time.LocalDateTime;

public interface TaskService {
    void insert(Task task);

    Task findTask(Long id);

    void delete(Long id);

    void update(Long id, String title_edited, String content_edited, LocalDateTime updated_time);
}
