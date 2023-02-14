package com.jhd.dotime.tasks.service;

import com.jhd.dotime.tasks.entity.Task;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Optional;

@Service
public interface TaskService {

    void insert(Task task);

    Optional<Task> findTask(Long id);

    //HashMap<Long, Task> findTaskList();

    void delete(Task task);

    void save(Task task);
}
