package com.jhd.dotime.tasks.service;


import com.jhd.dotime.tasks.dto.TaskResponseDto;
import com.jhd.dotime.tasks.dto.TaskSaveRequestDto;
import com.jhd.dotime.tasks.repository.TaskRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
//import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import javax.transaction.Transactional;
import java.util.List;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;


@ExtendWith(MockitoExtension.class)
public class TaskServiceTest {

    @MockBean
    private TaskRepository taskRepository;

    @Autowired
    private TaskService taskService;

    @BeforeEach
    public void beforeEach(){
        MockitoAnnotations.initMocks(this);
        this.taskService = new TaskServiceImpl(this.taskRepository);
    }
    @AfterEach
    public void afterEach(){
        taskRepository.deleteAllInBatch();
    }

    @Test
    @Transactional
    @DisplayName("새로운 Task 삽입")
    public void insert() {
        //given
        TaskSaveRequestDto task = TaskSaveRequestDto.builder().title("new task").content("new task unit test").build();

        //when
        taskService.insert(task);
        List<TaskResponseDto> taskLst = taskService.findTaskList();

        //then
        verify(taskRepository, times(taskLst.size())).save(task.toEntity());

    }
//
//
//    /*
//     * delete시에 taskId를 통해 해당 task가 더이상 존재하지 않는 것을 보여야한다.
//     */
//    @Test
//    @Transactional
//    @DisplayName("Task 삭제")
//    void delete() {
//        //given
//        //when
//        //then
//    }
//
//    @Test
//    @Transactional
//    @DisplayName("Task id로 조회")
//    void findById() {
//        //given
//        //when
//        //then
//
//    }
//
//    @Test
//    @Transactional
//    @DisplayName("Task 갱신")
//    void update() {
//        //given
//        //when
//        //then
//    }
//

}