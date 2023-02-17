package com.jhd.dotime.tasks.service;


import com.jhd.dotime.members.entity.Member;
import com.jhd.dotime.members.repository.MemberRepository;
import com.jhd.dotime.members.service.MemberServiceImpl;
import com.jhd.dotime.tasks.dto.TaskResponseDto;
import com.jhd.dotime.tasks.dto.TaskSaveRequestDto;
import com.jhd.dotime.tasks.dto.TaskUpdateRequestDto;
import com.jhd.dotime.tasks.entity.Task;
import com.jhd.dotime.tasks.repository.TaskRepository;
import org.assertj.core.api.Assertions;
import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;


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
        this.taskService = new TaskServiceImpl(this.taskRepository, this.memberRepository);
    }


    @Test
    @Transactional
    @DisplayName("[Service] Task 삽입")
    public void insert() {
        //given
        TaskSaveRequestDto task = TaskSaveRequestDto.builder().title("new task").content("new task unit test").build();
        //given
        Member newMember = Member.builder()
                .email("test@test.com")
                .password("1234")
                .username("testMan")
                .profileImage("")
                .build();

        memberRepository.save(newMember);
        //when

        List<Task> taskLst = new ArrayList<>();
        taskLst.add(task.toEntity(newMember));
        given(taskRepository.findAll()).willReturn(taskLst);
        given(taskRepository.save(any(Task.class))).willReturn(task.toEntity(newMember));
        //        when(taskService.insert(task)).thenReturn(1L);

        //then
        taskService.insert(newMember.getId(), task);
        List<TaskResponseDto> tmp = taskService.findTaskList();
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
        Task task = new Task(1L, newMember,"new Task", "Task Test");

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
        Task task = new Task(1L, newMember,"new Task", "Task Test");
        given(taskRepository.findById(task.getId())).willReturn(Optional.of(task));
        //when
        when(taskRepository.findById(task.getId())).thenReturn(Optional.of(task));
        //then
        TaskResponseDto findTask = taskService.findTask(1L);
        Assertions.assertThat(findTask.getId()).isEqualTo(task.getId());

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
        Task task = new Task(1L, newMember,"new Task", "Task Test");
        Task task2 = new Task(2L, newMember,"new Task", "Task Test");
        List<Task> taskList = new ArrayList<>();
        taskList.add(task);
        taskList.add(task2);
        given(taskRepository.findAll()).willReturn(taskList);
        //when

        //then

        List<TaskResponseDto> taskResLst = taskService.findTaskList();
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

        memberRepository.save(newMember);
        Task task = new Task(1L, newMember,"new Task", "Task Test");
        TaskUpdateRequestDto taskUpdateRequestDto = new TaskUpdateRequestDto("update task", "update task test22");
        given(taskRepository.findById(1L)).willReturn(Optional.of(task));

        //when
        Long id = taskService.update(1L, taskUpdateRequestDto);
        System.out.println("task.getTitle() = " + task.getTitle());

        //then
        Assertions.assertThat(id).isEqualTo(task.getId());
        Assertions.assertThat(taskUpdateRequestDto.getTitle()).isEqualTo(task.getTitle());

    }


}