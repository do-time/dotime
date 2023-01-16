package com.jhd.dotime.tasks.service;

import com.jhd.dotime.common.config.AppConfig;
import com.jhd.dotime.tasks.entity.Task;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.time.LocalDateTime;


class TaskServiceTest {

    TaskService taskService;

    @BeforeEach
    public void beforeEach() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
        taskService = ac.getBean(TaskService.class);
    }

    /*
     * insert의 필수값
     * member_id, title, created_date, updated_date
     *
     * 테스트 시에 주의 해야하는 부분
     *
     */
    @Test
    @DisplayName("새로운 Task 삽입")
    void insert() {
        //given
        LocalDateTime created_time = LocalDateTime.now();
        Task task = new Task(1L, "helloTask", created_time, created_time);

        //when
        taskService.insert(task);
        Task findTask = taskService.findTask(1L);

        //then
        System.out.println("findTask = " + findTask.getTitle());
        System.out.println("task = " + task.getTitle());
        Assertions.assertThat(task).isEqualTo(findTask);
    }


    /*
     * delete시에 taskId를 통해 해당 task가 더이상 존재하지 않는 것을 보여야한다.
     */
    @Test
    @DisplayName("Task 삭제")
    void delete() {
        //given
        LocalDateTime now = LocalDateTime.now();
        Task task = new Task(1L, "hello task", now, now);

        //when
        taskService.delete(1L);
        //then
        Task retTask = taskService.findTask(1L);
        Assertions.assertThat(retTask).isNull();
    }

    @Test
    @DisplayName("Task id로 조회")
    void findById() {
        //given
        LocalDateTime now = LocalDateTime.now();
        Long id = 1L;
        Task task = new Task(id, "hello task", now, now);


        //when
        taskService.insert(task);
        Task returnedTask = taskService.findTask(id);

        //then
        Assertions.assertThat(returnedTask).isEqualTo(task);
    }

    @Test
    @DisplayName("Task 갱신")
    void update() {
        //given
        //when
        //then
    }


}