package com.jhd.dotime.tasks.repository;

import com.jhd.dotime.common.config.AppConfig;
import com.jhd.dotime.tasks.entity.Task;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.time.LocalDateTime;



class TaskRepositoryTest {


    TaskRepository taskRepository;

    @BeforeEach
    public void beforeEach(){
//        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
//        taskRepository = ac.getBean("taskRepository", TaskRepository.class);
        taskRepository = new MemoryTaskRepository();
    }

    @Test
    @DisplayName("task 저장")
    void store(){
        //given
        LocalDateTime now = LocalDateTime.now();
        Task newTask = new Task(1L, "new task", now, now);
        //when
        taskRepository.store(newTask);
        //then
        Assertions.assertThat(newTask).isEqualTo(taskRepository.findTask(1L));
    }

    @Test
    @DisplayName("id로 task 조회")
    void findTask(){
        //given
        LocalDateTime now = LocalDateTime.now();
        Task task = new Task(1L, "hello Task", now, now);
        taskRepository.store(task);
        //when
        Task findTask = taskRepository.findTask(1L);
        //then
        Assertions.assertThat(findTask).isEqualTo(task);
    }

    @Test
    @DisplayName("task 업데이트")
    void update(){
        //given
        LocalDateTime now = LocalDateTime.now();
        Task newTask = new Task(1L, "new task", now, now);
        //when
        taskRepository.store(newTask);
        taskRepository.update(1L, "title edited", "this is edited content",now);
        System.out.println("newTask.getTitle() = " + newTask.getTitle() + " newTask.updatedtime() = " + newTask.getUpdated_date());

        //then
        Assertions.assertThat(taskRepository.findTask(1L).getUpdated_date()).isEqualTo(now);
    }

}