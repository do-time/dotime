package com.jhd.dotime.tasks.controller;


import com.jhd.dotime.tasks.entity.Task;

import com.jhd.dotime.tasks.repository.TaskRepository;
import com.jhd.dotime.tasks.service.TaskService;
import com.jhd.dotime.tasks.service.TaskServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.HashMap;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;



@WebMvcTest
@RunWith(SpringRunner.class)
class TaskControllerTest {
    /*
     * mock을 사용하지 않는다면 app Config를 사용해야했음.
     * mock bean을 통해 test를 진행할 수 있음.
     */

//    @MockBean
//    TaskRepository taskRepository;

    @MockBean
    TaskService taskService;

    @MockBean
    TaskController taskController;

    @Autowired
    MockMvc mvc;

//    @BeforeEach
//    public void setUp() {
//        this.taskService = new TaskServiceImpl(taskRepository);
//        this.taskController = new TaskController(this.taskService);
//    }


    @Test
    @DisplayName("Task 조회하기")
    void getTask() throws Exception{
        //given
        LocalDateTime now = LocalDateTime.now();
        Task task = Task.builder().title("task").content("nono").created_date(now).updated_date(now).build();
        taskService.insert(task);
        //when
        Assertions.assertThat(taskService.findTask(task.getId())).isEqualTo(task);
        //then
        mvc.perform(get("/api/v1/task/"+task.getId().toString())
                        .param("id", task.getId().toString()))
                .andExpect(status().isOk());

    }


    @Test
    @DisplayName("Task 삽입하기")
    void insertTask(){

    }

    @Test
    @DisplayName("Task 삭제하기")
    void deleteTask(){

    }

//    @Test
//    @DisplayName("Task 목록 조회하기")
//    void getTaskList(){
//        //given
//        LocalDateTime now = LocalDateTime.now();
//        LocalDateTime now = LocalDateTime.now();
//        Task task = Task.builder().title("task").content("nono").created_date(now).updated_date(now).build();
//
//
//        taskService.insert(task);
//
//
//
//        data.put(task.getId(), task);
//
//
//        //when
//
//        //then
//
//    }


    @AfterEach
    void tearDown() {
    }
}