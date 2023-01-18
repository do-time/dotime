package com.jhd.dotime.tasks.controller;

import com.jhd.dotime.common.config.AppConfig;
import com.jhd.dotime.members.service.MemberService;
import com.jhd.dotime.tasks.entity.Task;
import com.jhd.dotime.tasks.service.TaskService;
import com.jhd.dotime.tasks.service.TaskServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("api/v1")
public class TaskController {

    private TaskService taskService;

    public TaskController(TaskServiceImpl taskService){
        this.taskService = taskService;
    }


    @GetMapping("/task/{id}")
    public Task getTask(Long id){
        return taskService.findTask(id);
    }
}
