package com.jhd.dotime.tasks.controller;

import com.jhd.dotime.common.config.AppConfig;
import com.jhd.dotime.tasks.entity.Task;
import com.jhd.dotime.tasks.repository.TaskRepository;
import com.jhd.dotime.tasks.service.TaskService;
import com.jhd.dotime.tasks.service.TaskServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;

import java.time.LocalDateTime;
import java.util.HashMap;


import static org.hamcrest.Matchers.containsString;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;


@WebMvcTest
class TaskControllerTest {


    /*
     * mock을 사용하지 않는다면 app Config를 사용해야했음.
     * mock bean을 통해 test를 진행할 수 있음.
     */

    TaskService taskService;

    @Autowired
    MockMvc mvc;

    @BeforeEach
    void setUp() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
        taskService = ac.getBean("taskService", TaskService.class);
    }


    @Test
    @DisplayName("Task 조회하기")
    void getTask() throws Exception{
        //given
        LocalDateTime now = LocalDateTime.now();
        Task task = new Task(1L, "hello controller", now, now);
        taskService.insert(task);
        //when
        Assertions.assertThat(taskService.findTask(task.getId())).isEqualTo(task);
        //then
        mvc.perform(get("/api/v1/task"))
                .andExpect(status().isOk())
                .andExpect((ResultMatcher) content().string(containsString("hello controller")));
    }


    @Test
    @DisplayName("Task 삽입하기")
    void insertTask(){

    }

    @Test
    @DisplayName("Task 삭제하기")
    void deleteTask(){

    }

    @Test
    @DisplayName("Task 목록 조회하기")
    void getTaskList(){
        //given
        LocalDateTime now = LocalDateTime.now();
        Task task = new Task(1L, "hello task", now, now);
        Task task2 = new Task(2L, "bye task", now, now);

        taskService.insert(task);
        taskService.insert(task2);

        HashMap<Long, Task> data = new HashMap<>();
        data.put(task.getId(), task);
        data.put(task2.getId(), task2);

        //when

        //then

    }


    @AfterEach
    void tearDown() {
    }
}