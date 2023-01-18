package com.jhd.dotime.tasks.repository;

import com.jhd.dotime.tasks.entity.Task;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.HashMap;


@Repository
public class MemoryTaskRepository implements TaskRepository{
    private static HashMap<Long, Task> data = new HashMap<>();


    @Override
    public Task findTask(Long id) {
        return data.get(id);
    }

    @Override
    public void store(Task newTask) {
        data.put(newTask.getId(), newTask);
    }

    @Override
    public void delete(Long id) {
        data.remove(id);
    }

    @Override
    public void update(Long id, String title_edited, String content_edited, LocalDateTime updated_time) {
        Task task = data.get(id);
        task.setTitle(title_edited);
        task.setContent(content_edited);
        task.setUpdated_date(updated_time);
        data.replace(id, task);
    }

    @Override
    public HashMap<Long, Task> getTaskList() {
        return data;
    }
}
