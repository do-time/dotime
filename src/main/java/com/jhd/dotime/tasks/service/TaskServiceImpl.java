package com.jhd.dotime.tasks.service;

import com.jhd.dotime.members.repository.MemberRepository;
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

        return taskRepository.save(taskSaveRequestDto.toEntity()).getId();
    }

    @Override
    @Transactional(readOnly = true)
    public TaskResponseDto findTask(Long id) {
        Task entity = taskRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 작업이 없습니다. id = " + id));
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
                .orElseThrow(() -> new IllegalArgumentException("해당 작업이 없습니다. id = " + id));
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
                .orElseThrow(() -> new IllegalArgumentException("해당 작업이 없습니다. id = " + id));

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
