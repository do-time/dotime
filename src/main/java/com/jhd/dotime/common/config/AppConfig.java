package com.jhd.dotime.common.config;

import com.jhd.dotime.tasks.repository.MemoryTaskRepository;
import com.jhd.dotime.tasks.repository.TaskRepository;
import com.jhd.dotime.tasks.service.TaskService;
import com.jhd.dotime.tasks.service.TaskServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public TaskService taskService(){
        return new TaskServiceImpl(taskRepository());
    }

    @Bean
    public TaskRepository taskRepository(){
        return new MemoryTaskRepository();
    }
}
