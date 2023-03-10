package com.jhd.dotime.tasks.service;

import com.jhd.dotime.common.error.ErrorCode;
import com.jhd.dotime.common.exception.CustomException;
import com.jhd.dotime.members.repository.MemberRepository;
import com.jhd.dotime.tasks.common.error.TaskErrorCode;
import com.jhd.dotime.tasks.common.exception.TaskException;
import com.jhd.dotime.tasks.dto.TaskSaveRequestDto;
import com.jhd.dotime.tasks.dto.TaskUpdateRequestDto;
import com.jhd.dotime.tasks.dto.TaskResponseDto;
import com.jhd.dotime.tasks.entity.Task;
import com.jhd.dotime.tasks.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService{
    private final TaskRepository taskRepository;

    private final MemberRepository memberRepository;

    @Override
    @Transactional
    public Long insert(Long memberId, TaskSaveRequestDto taskSaveRequestDto) {
        Task newTask = Task.builder()
                .member(memberRepository.findById(memberId).get())
                .title(taskSaveRequestDto.getTitle())
                .content(taskSaveRequestDto.getContent())
                .build();

        //* 같은 이름의 title이 task list에 존재하는지 확인 있다면 DUPLICATE_RESOURCE 처리 *//
        List<Task> taskLst = taskRepository.findTaskListByMemberId(memberId);
        for (Task task : taskLst) {
            if(task.getTitle().equals(newTask)) throw new CustomException(ErrorCode.DUPLICATE_RESOURCE);

        }


        return taskRepository.save(newTask).getId();
    }


    @Override
    @Transactional(readOnly = true)
    public TaskResponseDto findTask(Long id) {
        Task entity = taskRepository.findById(id)
                .orElseThrow(() -> new TaskException(TaskErrorCode.TASK_NOT_FOUNT));
        return new TaskResponseDto(entity);
    }

    @Override
    @Transactional(readOnly = true)
    public List<TaskResponseDto> findTaskList() {
        return taskRepository.findAll().stream()
                .map(TaskResponseDto::new)
                .collect(Collectors.toList());
    }


    @Transactional
    public Long delete(Long id) {
        Task tasks = taskRepository.findById(id)
                .orElseThrow(() -> new TaskException(TaskErrorCode.TASK_NOT_FOUNT));
        taskRepository.delete(tasks);
        System.out.println("tasks.getTitle() = " + tasks.getTitle() + " tasks.id = " + tasks.getId());
        return id;
    }

    @Transactional
    public void save(Task task) {
        taskRepository.save(task);
    }

    @Override
    @Transactional
    public Long update(Long id, TaskUpdateRequestDto taskUpdateRequestDto) {
        Task tasks = taskRepository.findById(id)
                .orElseThrow(() -> new TaskException(TaskErrorCode.TASK_NOT_FOUNT));

        tasks.update(taskUpdateRequestDto.getTitle(), taskUpdateRequestDto.getContent());
        return id;
    }

    @Override
    @Transactional(readOnly = true)
    public List<TaskResponseDto> getTaskListByMemberId(Long memberId){
        return taskRepository.findTaskListByMemberId(memberId).stream()
                .map(TaskResponseDto::new)
                .collect(Collectors.toList());
    }



}
