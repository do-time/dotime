package com.jhd.dotime.tasks.repository;


import com.jhd.dotime.members.entity.Member;
import com.jhd.dotime.members.repository.MemberRepository;
import com.jhd.dotime.tasks.entity.Task;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;


@ActiveProfiles("test") // application-test
@SpringBootTest
class TaskRepositoryTest {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private TaskRepository taskRepository;



    @Test
    @DisplayName("[Repository] task 조회")
    public void getTask() {
        //given
        Member member = Member.builder()
                .email("test@test.com")
                .password("1234")
                .username("testMan")
                .profileImage("")
                .build();
        Member newMember = memberRepository.save(member);
        Task newTask = Task.builder()
                .member(newMember)
                .title("test1")
                .content("test11")
                .build();

        //when
        taskRepository.save(newTask);
        List<Task> lst = taskRepository.findAll();

        //then
        Assertions.assertThat(taskRepository.findById(lst.get(lst.size() - 1).getId()).get().getTitle()).isEqualTo(newTask.getTitle());

    }

    @Test
    @DisplayName("member로 task 목록 조회")
    public void insertTaskWithMember() {
        //given
        Member member = Member.builder()
                .email("test@test.com")
                .password("1234")
                .username("testMan")
                .profileImage("")
                .build();
        Member newMember = memberRepository.save(member);

        Task task1 = Task.builder()
                .member(member)
                .title("test1")
                .content("test11")
                .build();

        taskRepository.save(task1);

        //when
        List<Task> findTask = taskRepository.findTaskListByMemberId(newMember.getId());
        for (Task task : findTask) {
            System.out.println("task.getTitle() = " + task.getTitle());

        }

        //then
//        Assertions.assertThat(findTask.stream().count()).isEqualTo(1);
        Assertions.assertThat(findTask.get(0).getTitle()).isEqualTo(task1.getTitle());
    }

    @Test
    @DisplayName("[Repository] task 저장")
    public void store() {
        //given
        Member member = Member.builder()
                .email("test@test.com")
                .password("1234")
                .username("testMan")
                .profileImage("")
                .build();
        Member newMember = memberRepository.save(member);

        Task newTask = Task.builder()
                .member(member)
                .title("test1")
                .content("test11")
                .build();

        //when
        taskRepository.save(newTask);
        List<Task> taskList = taskRepository.findTaskListByMemberId(member.getId());


        //then
        Assertions.assertThat(newTask.getTitle()).isEqualTo(taskList.get(taskList.size() - 1).getTitle());
    }

    @Test
    @DisplayName("[Repository] task 삭제")
    public void delete() {
        //given
        Member member = Member.builder()
                .email("test@test.com")
                .password("1234")
                .username("testMan")
                .profileImage("")
                .build();
        Member newMember = memberRepository.save(member);

        //when
        Task newTask = Task.builder()
                .member(member)
                .title("test1")
                .content("test11")
                .build();
        taskRepository.save(newTask);
        List<Task> taskList = taskRepository.findAll();
        for (Task task : taskList) {
            System.out.println("task.getTitle() = " + task.getTitle());
        }
        taskRepository.delete(newTask);
        taskList = taskRepository.findTaskListByMemberId(memberRepository.findByEmail(newMember.getEmail()).get().getId());

        boolean success = taskList.contains(newTask);

        //then
        Assertions.assertThat(success).isEqualTo(false);
    }


}