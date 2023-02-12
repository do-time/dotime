package com.jhd.dotime.tasks.service;

import com.jhd.dotime.tasks.entity.Task;
import com.jhd.dotime.tasks.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Optional;


@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService{


    private final TaskRepository taskRepository;

//    @Autowired
//    public TaskServiceImpl(TaskRepository taskRepository){
//        this.taskRepository = taskRepository;
//    }


    @Override
    public void insert(Task task) {
        taskRepository.save(task);
    }

    @Override
    public Optional<Task> findTask(Long id) {
        return taskRepository.findById(id);
    }

//    @Override
//    public HashMap<Long, Task> findTaskList() {
//        return taskRepository.findAll();
//    }


    public void delete(Task task) {
        taskRepository.delete(task);
    }



    public void save(Task task) {
        taskRepository.save(task);
    }
}
