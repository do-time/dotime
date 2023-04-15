package com.jhd.dotime.tasktimelog.service;

import com.jhd.dotime.members.entity.Member;
import com.jhd.dotime.members.repository.MemberRepository;
import com.jhd.dotime.tasks.dto.TaskDto;
import com.jhd.dotime.tasks.entity.Task;
import com.jhd.dotime.tasks.repository.TaskRepository;
import com.jhd.dotime.tasktimelog.dto.TaskTimeLogDto;
import com.jhd.dotime.tasktimelog.entity.TaskTimeLog;
import com.jhd.dotime.tasktimelog.mapper.TaskTimeLogMapper;
import com.jhd.dotime.tasktimelog.repository.TaskTimeLogRepository;
import org.junit.Before;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TaskTimeLogServiceTest {
    @InjectMocks
    TaskTimeLogServiceImpl taskTimeLogService;

    @Mock
    TaskTimeLogRepository taskTimeLogRepository;

    @Mock
    private MemberRepository memberRepository;

    @Mock
    private TaskRepository taskRepository;

    @Before
    public void beforeEach(){
        MockitoAnnotations.initMocks(this);
    }

    private TaskTimeLogMapper taskTimeLogMapper = Mappers.getMapper(TaskTimeLogMapper.class);

    @Test
    @DisplayName("[Service] task 시작")
    public void startTimeLogTest(){
        //given
        Member member = Member.builder()
                .email("test@test.com")
                .password("1234")
                .username("testMan")
                .profileImage("")
                .build();

        memberRepository.save(member);

        Task task = Task.builder()
                .member(member)
                .title("테스트 task")
                .content("timelog start test")
                .build();

        taskRepository.save(task);

        TaskTimeLog taskTimeLog = taskTimeLogMapper.toEntity(task);

        TaskTimeLogDto.Request requestDto = new TaskTimeLogDto.Request(1L);
        given(taskRepository.findById(requestDto.getId())).willReturn(Optional.of(task));
        given(taskTimeLogRepository.save(any(TaskTimeLog.class))).willReturn(taskTimeLog);

        //when
        taskTimeLogService.startTimeLog(requestDto);

        //then
        //creation은 뭘로 검증해야하지?
    }
}