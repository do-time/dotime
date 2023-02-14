package com.jhd.dotime.tasks.service;


import com.jhd.dotime.tasks.dto.TaskSaveRequestDto;
import com.jhd.dotime.tasks.entity.Task;
import com.jhd.dotime.tasks.repository.TaskRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

//@RequiredArgsConstructor
@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class TaskServiceTest {

    @Mock
    private TaskRepository taskRepository;


    @InjectMocks
    private TaskServiceImpl taskService;

    @AfterEach
    public void afterEach(){
        taskRepository.deleteAllInBatch();

    }

    /*
     * insert의 필수값
     * member_id, title, created_date, updated_date
     *
     * 테스트 시에 주의 해야하는 부분
     *
     */
    @Test
    @Transactional
    @DisplayName("새로운 Task 삽입")
    public void insert() {
        //given
        LocalDateTime now = LocalDateTime.now();
//        TaskSaveRequestDto task = TaskSaveRequestDto.builder().title("helloTask").content("nono").build();
//        taskService.insert(task);
//        List<TaskSaveRequestDto> taskList = new ArrayList<>();
//        taskList.add(task);
//        given(taskRepository.findAll()).willReturn(taskList);
        //when
//        List<Task> findTask = taskRepository.findAll();


        //then
//        Assertions.assertThat(task.getTitle()).isEqualTo(findTask.get(0).getTitle());
//        System.out.println("findTask.get(0).getTitle() = " + findTask.get(0).getTitle());
//        System.out.println("task.getTitle() = " + task.getTitle());
    }


    /*
     * delete시에 taskId를 통해 해당 task가 더이상 존재하지 않는 것을 보여야한다.
     */
    @Test
    @Transactional
    @DisplayName("Task 삭제")
    void delete() {
        //given
        Task task = Task.builder().title("helloTask").content("its me").build();

        //when
        List<Task> taskLst = new ArrayList<>();
        taskLst.add(task);
        given(taskRepository.findAll()).willReturn(taskLst);

        System.out.println("task added -> " + taskRepository.findAll().get(0).getContent());

        //then

        Mockito.verify(taskRepository, times(1)).delete(task);
//                List<Task> retTask = taskRepository.findAll();

//        Assertions.assertThat(retTask.get(0)).isNull();
    }

    @Test
    @Transactional
    @DisplayName("Task id로 조회")
    void findById() {
//        //given
//        LocalDateTime now = LocalDateTime.now();
//        Task task = new Task("hello task","its me", now, now);
//
//
//        //when
//        taskService.insert(task);
//        Optional<Task> returnedTask = taskService.findTask(id);
//
//        //then
//        Assertions.assertThat(returnedTask).isEqualTo(task);
    }

    @Test
    @Transactional
    @DisplayName("Task 갱신")
    void update() {
        //given
        //when
        //then
    }


}