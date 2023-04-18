package com.jhd.dotime.tasktime.service;

import com.jhd.dotime.members.entity.Member;
import com.jhd.dotime.members.repository.MemberRepository;
import com.jhd.dotime.tasks.entity.Task;
import com.jhd.dotime.tasks.repository.TaskRepository;
import com.jhd.dotime.tasktime.dto.TaskTimeLogDto;
import com.jhd.dotime.tasktime.entity.TaskTimeLog;
import com.jhd.dotime.tasktime.mapper.TaskTimeLogMapper;
import com.jhd.dotime.tasktime.repository.TaskTimeLogRepository;
import org.junit.Before;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class TaskTimeLogServiceTest {
    Member member;
    Task task;
    private TaskTimeLogMapper taskTimeLogMapper = Mappers.getMapper(TaskTimeLogMapper.class);
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

    @BeforeAll
    public void beforeAll() {
        member = Member.builder()
                .email("test@test.com")
                .password("1234")
                .username("testMan")
                .profileImage("")
                .build();

        task = Task.builder()
                .member(member)
                .title("테스트 task")
                .content("timelog start test")
                .build();
    }

    @Test
    @DisplayName("[Service] task 시작 성공 테스트")
    public void startTimeLogTest(){
        //given
        TaskTimeLogDto.Request requestDto = new TaskTimeLogDto.Request(null, 1L, LocalDateTime.now(), null);
        TaskTimeLog taskTimeLog = taskTimeLogMapper.toEntity(requestDto, task);

        given(taskRepository.findById(requestDto.getTaskId())).willReturn(Optional.of(task));
        given(taskTimeLogRepository.save(any(TaskTimeLog.class))).willReturn(taskTimeLog);

        //when
        TaskTimeLogDto.Response responseDto = taskTimeLogService.startTimeLog(requestDto);

        //then
        //creation은 뭘로 검증해야하지?
    }

    @Test
    @DisplayName("[Service] task 정지 성공 테스트")
    public void pauseTimeLogTest(){
        //given
        TaskTimeLogDto.Request requestDto = new TaskTimeLogDto.Request(1L, 1L, null, LocalDateTime.now());
        TaskTimeLog taskTimeLog = taskTimeLogMapper.toEntity(requestDto, task);

        given(taskRepository.findById(requestDto.getTaskId())).willReturn(Optional.of(task));
        given(taskTimeLogRepository.findById(requestDto.getId())).willReturn(Optional.of(taskTimeLog));

        //when
        TaskTimeLogDto.Response responseDto = taskTimeLogService.pauseTimeLog(requestDto);

        //then
        assertAll(
                () -> assertNotNull(responseDto),
                () -> responseDto.getEndTime().equals(requestDto.getEndTime())
        );

    }
}