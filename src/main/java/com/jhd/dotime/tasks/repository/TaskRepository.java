package com.jhd.dotime.tasks.repository;

import com.jhd.dotime.tasks.entity.Task;

import java.time.LocalDateTime;
import java.util.HashMap;


public interface TaskRepository {

    Task findTask(Long id);

    void store(Task newTask);

    void delete(Long id);

    void update(Long id, String title_edited, String content, LocalDateTime updated_time);

    HashMap<Long, Task> getTaskList();
}
