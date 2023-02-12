package com.jhd.dotime.tasks.controller;

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
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
@RequestMapping("api/v1")
public class TaskController {


    private final TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService){
        this.taskService = taskService;
    }


    @GetMapping("/task/{id}")
    public Optional<Task> getTask(@RequestParam Long id){
        return taskService.findTask(id);
    }
}
