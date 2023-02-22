package com.jhd.dotime;

import com.jhd.dotime.tasks.repository.TaskRepository;
import com.jhd.dotime.tasks.service.TaskService;
import com.jhd.dotime.tasks.service.TaskServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

//    @Autowired
//    TaskRepository taskRepository;
//
//    @Bean
//    public TaskService taskService(){
//        return new TaskServiceImpl(taskRepository());
//    }
//
//    @Bean
//    public TaskRepository taskRepository(){
//        return taskRepository;
//    }
}
