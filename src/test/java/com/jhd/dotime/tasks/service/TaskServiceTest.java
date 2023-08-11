package com.jhd.dotime.tasks.service;


import com.jhd.dotime.hashtag.entity.HashTag;
import com.jhd.dotime.hashtag.entity.TaskTag;
import com.jhd.dotime.members.entity.Member;
import com.jhd.dotime.members.repository.MemberRepository;


import com.jhd.dotime.tasks.common.error.TaskErrorCode;
import com.jhd.dotime.tasks.common.exception.TaskException;
import com.jhd.dotime.tasks.dto.TaskDto;
import com.jhd.dotime.tasks.entity.Task;
import com.jhd.dotime.tasks.repository.TaskRepository;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

//@SpringBootTest
//@ActiveProfiles("test") // application-test
@ExtendWith(MockitoExtension.class)
class TaskServiceTest {

    @Mock
    private TaskRepository taskRepository;

    @InjectMocks
    private TaskServiceImpl taskService;

    @Mock
    private MemberRepository memberRepository;

    @Before
    public void beforeEach(){
        MockitoAnnotations.initMocks(this);
//        this.taskService = new TaskServiceImpl(this.taskRepository, this.memberRepository);
    }


    @Test
    @Transactional
    @DisplayName("[Service] Task 삽입")
    public void insert() {
        //given
        TaskDto.Request task = TaskDto.Request.builder().title("new task").hashtag("#hello#bye").content("new task unit test").build();
        //given
        Member newMember = Member.builder()
                .email("test@test.com")
                .password("1234")
                .username("testMan")
                .profileImage("")
                .build();

        memberRepository.save(newMember);
        //when
        Task newTask = Task.builder()
                .member(newMember)
                .title(task.getTitle())
                .content(task.getContent())
                .build();
        List<Task> taskLst = new ArrayList<>();
        taskLst.add(Task.builder()
                        .member(newMember)
                        .title(task.getTitle())
                        .content(task.getContent())
                .build());
        given(taskRepository.findAll()).willReturn(taskLst);
        given(taskRepository.save(any(Task.class))).willReturn(newTask);
        //        when(taskService.insert(task)).thenReturn(1L);

        //then
        taskService.insert(newMember.getId(), task);
        List<TaskDto.Response> tmp = taskService.findTaskList();
        Assertions.assertThat(tmp.get(0).getTitle()).isEqualTo(task.getTitle());

    }


    /*
     * delete시에 taskId를 통해 해당 task가 더이상 존재하지 않는 것을 보여야한다.
     */
    @Test
    @Transactional
    @DisplayName("[Service] Task 삭제")
    void delete() {
        //given
        Member newMember = Member.builder()
                .email("test@test.com")
                .password("1234")
                .username("testMan")
                .profileImage("")
                .build();

        memberRepository.save(newMember);
        Task task = new Task(1L, newMember, "test1","new Task", new ArrayList<>(), new ArrayList<>()) ;

        //when
        given(taskRepository.findById(task.getId())).willReturn(Optional.of(task));

        //then
        Long id = taskService.delete(1L);
        Assertions.assertThat(id).isEqualTo(1L);

    }

    @Test
    @Transactional
    @DisplayName("[Service] Task id로 조회")
    void findById() {
        //given
        Member newMember = Member.builder()
                .email("test@test.com")
                .password("1234")
                .username("testMan")
                .profileImage("")
                .build();

        memberRepository.save(newMember);
        Task task = new Task(1L, newMember, "test1","new Task", new ArrayList<>(), new ArrayList<>());
        given(taskRepository.findById(task.getId())).willReturn(Optional.of(task));

        //when
        when(taskRepository.findById(task.getId())).thenReturn(Optional.of(task));

        //then
        List<TaskDto.Response> findTask = taskService.findTask(1L);
        Assertions.assertThat(findTask.get(0).getId()).isEqualTo(task.getId());

    }

    @Test
    @Transactional
    @DisplayName("[Service] Task 전체 조회")
    void findTaskList() {
        //given
        Member newMember = Member.builder()
                .email("test@test.com")
                .password("1234")
                .username("testMan")
                .profileImage("")
                .build();

        memberRepository.save(newMember);
        Task task1 = new Task(1L, newMember, "test1","new Task", new ArrayList<>(), new ArrayList<>());
        Task task2 = new Task(2L, newMember, "test1","new Task", new ArrayList<>(), new ArrayList<>());
        List<Task> taskList = new ArrayList<>();
        taskList.add(task1);
        taskList.add(task2);
        given(taskRepository.findAll()).willReturn(taskList);
        //when

        //then

        List<TaskDto.Response> taskResLst = taskService.findTaskList();
        Assertions.assertThat(taskResLst.get(0).getTitle()).isEqualTo(taskList.get(0).getTitle());

    }

    @Test
    @Transactional
    @DisplayName("[Service] Task 갱신")
    void update() {
        //given
        Member newMember = Member.builder()
                .email("test@test.com")
                .password("1234")
                .username("testMan")
                .profileImage("")
                .build();

        Task task = new Task(1L, newMember, "test1","new Task", new ArrayList<>(), new ArrayList<>());

        TaskDto.Request taskDtoRequest = new TaskDto.Request("update task", "update task test22", "", new ArrayList<>());

        memberRepository.save(newMember);
        taskRepository.save(task);

        //when
        given(taskRepository.findById(1L)).willReturn(Optional.of(task));
        Long id = taskService.update(1L, taskDtoRequest, new ArrayList<>());

//        Task findTask = taskRepository.findById(1L).orElseThrow(() -> new TaskException(TaskErrorCode.TASK_NOT_FOUNT));

        //then
        Assertions.assertThat(id).isEqualTo(task.getId());
//        Assertions.assertThat(findTask.getTitle()).isEqualTo(task.getTitle());

    }


}