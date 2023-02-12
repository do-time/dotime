package com.jhd.dotime.tasks.service;


import com.jhd.dotime.tasks.entity.Task;
import com.jhd.dotime.tasks.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;

import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


import java.time.LocalDateTime;
import java.util.Optional;

//@RequiredArgsConstructor
@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class TaskServiceTest {

    @Mock
    private TaskRepository taskRepository;


    TaskService taskService;

    @BeforeEach
    public void beforeEach(){
        MockitoAnnotations.initMocks(this);
        this.taskService = new TaskServiceImpl(taskRepository);
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
    public void insert() {
        //given
        LocalDateTime now = LocalDateTime.now();
        Task task = Task.builder().title("helloTask").content("nono").created_date(now).updated_date(now).build();

        //when
        taskService.insert(task);
        Optional<Task> findTask = taskService.findTask(1L);

        //then
        System.out.println("findTask = " + findTask);
        System.out.println("task = " + task.getTitle());
//        Assertions.assertThat(task).isEqualTo(findTask);
    }

//
//    /*
//     * delete시에 taskId를 통해 해당 task가 더이상 존재하지 않는 것을 보여야한다.
//     */
//    @Test
//    @DisplayName("Task 삭제")
//    void delete() {
//        //given
////        LocalDateTime now = LocalDateTime.now();
////        Task task = new Task(1L, "hello task", now, now);
//
//        //when
//        taskService.delete(this.task);
//        //then
//        Optional<Task> retTask = taskService.findTask(1L);
//        Assertions.assertThat(retTask).isNull();
//    }
//
//    @Test
//    @DisplayName("Task id로 조회")
//    void findById() {
//        //given
////        LocalDateTime now = LocalDateTime.now();
//        Long id = 1L;
////        Task task = new Task(id, "hello task", now, now);
//
//
//        //when
//        taskService.insert(task);
//        Optional<Task> returnedTask = taskService.findTask(id);
//
//        //then
//        Assertions.assertThat(returnedTask).isEqualTo(task);
//    }
//
//    @Test
//    @DisplayName("Task 갱신")
//    void update() {
//        //given
//        //when
//        //then
//    }


}