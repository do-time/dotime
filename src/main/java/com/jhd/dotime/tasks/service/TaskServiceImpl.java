package com.jhd.dotime.tasks.service;

import com.jhd.dotime.tasks.entity.Task;
import com.jhd.dotime.tasks.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;


@Service
public class TaskServiceImpl implements TaskService{
    private final TaskRepository taskRepository;

    @Autowired
    public TaskServiceImpl(TaskRepository taskRepository){
        this.taskRepository = taskRepository;
    }
    @Override
    public void insert(Task task) {
        taskRepository.store(task);
    }

    @Override
    public Task findTask(Long id) {
        return taskRepository.findTask(id);
    }

    @Override
    public HashMap<Long, Task> findTaskList() {
        return taskRepository.getTaskList();
    }

    @Override
    public void delete(Long id) {
        taskRepository.delete(id);
    }

    @Override
    public void update(Long id, String title_edited, String content_edited, LocalDateTime updated_time) {
        taskRepository.update(id, title_edited, content_edited, updated_time);
    }
}
