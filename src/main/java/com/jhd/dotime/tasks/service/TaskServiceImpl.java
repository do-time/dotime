package com.jhd.dotime.tasks.service;

import com.jhd.dotime.common.error.ErrorCode;
import com.jhd.dotime.common.exception.CustomException;
import com.jhd.dotime.hashtag.entity.HashTag;
import com.jhd.dotime.hashtag.entity.TaskTag;
import com.jhd.dotime.hashtag.repository.HashTagRepository;
import com.jhd.dotime.hashtag.repository.TaskTagRepository;
import com.jhd.dotime.members.common.error.MemberErrorCode;
import com.jhd.dotime.members.common.exception.NotFoundException;
import com.jhd.dotime.members.entity.Member;
import com.jhd.dotime.members.repository.MemberRepository;
import com.jhd.dotime.tasks.common.error.TaskErrorCode;
import com.jhd.dotime.tasks.common.exception.TaskException;
import com.jhd.dotime.tasks.dto.TaskDto;

import com.jhd.dotime.tasks.entity.Task;
//import com.jhd.dotime.tasks.mapper.TaskMapper;
import com.jhd.dotime.tasks.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService{
    private final TaskRepository taskRepository;

    private final MemberRepository memberRepository;

    private final TaskTagRepository taskTagRepository;

    private final HashTagRepository hashTagRepository;

//    private final TaskMapper taskMapper = Mappers.getMapper(TaskMapper.class);


    @Override
    @Transactional
    public Long insert(Long memberId, TaskDto.Request taskRequestDto) {
//        Task newTask = taskMapper.toEntity(taskRequestDto, memberRepository.findById(memberId).orElseThrow(() -> new CustomException(MemberErrorCode.MEMBER_NOT_FOUND)));
//        Task newTask = Task
        Member member = memberRepository.findById(memberId).orElseThrow(() -> new CustomException(MemberErrorCode.MEMBER_NOT_FOUND));
//
//        //* 같은 이름의 title이 task list에 존재하는지 확인 있다면 DUPLICATE_RESOURCE 처리 *//
//        List<Task> taskLst = taskRepository.findTaskListByMemberId(memberId);
//        for (Task task : taskLst) {
//            if(task.getTitle().equals(newTask)) throw new CustomException(ErrorCode.DUPLICATE_RESOURCE);
//
//        }

        return taskRepository.save(Task.builder()
                .member(member)
                .content(taskRequestDto.getContent())
                .title(taskRequestDto.getTitle())
                .build()).getId();
    }


    @Override
    @Transactional(readOnly = true)
    public List<TaskDto.Response> findTask(Long id) {
        Task entity = taskRepository.findById(id)
                .orElseThrow(() -> new TaskException(TaskErrorCode.TASK_NOT_FOUNT));
        List<TaskTag> taskTagList = taskTagRepository.findTaskTagByTaskId(id);
        List<HashTag> hashTagList = new ArrayList<>();

        for (TaskTag taskTag : taskTagList) {
            hashTagList.add(taskTag.getHashTag());
        }

        return taskRepository.findById(id).stream()
                .map(TaskDto.Response::of)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<TaskDto.Response> findTaskList() {
        return taskRepository.findAll().stream()
                .map(TaskDto.Response::of)
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
    public Long update(Long id, TaskDto.Request taskRequestDto, List<Long> hashtagIdList) {
        Task tasks = taskRepository.findById(id)
                .orElseThrow(() -> new TaskException(TaskErrorCode.TASK_NOT_FOUNT));

        List<TaskTag> taskTagList = taskTagRepository.findTaskTagByTaskId(id);
        List<TaskTag> taskTags = new ArrayList<>();


        // 있으면 하고 없으면 삭제 안함, taskTagList - 기존 taskTagList
        for (TaskTag taskTag : taskTagList) {
            if(!hashtagIdList.contains(taskTag.getHashTag().getId()))
                continue;

            taskTagRepository.delete(taskTag);
        }

        List<Long> newHasktagIdList = taskTagRepository.findTaskTagByTaskId(id).stream().map(TaskTag::getHashTag).map(HashTag::getId).collect(Collectors.toList());

        // 업데이트 시에도 이미 tasktag list에 해당 해시태그 존재하면 add 일어나면 안됨
        for (Long hashtagId : hashtagIdList) {
            HashTag hashTag = hashTagRepository.findById(hashtagId).orElseThrow(() -> new NotFoundException("해시태그가 존재하지 않습니다."));
            if(newHasktagIdList.contains(hashtagId))
                continue;
            taskTags.add(TaskTag.builder().task(tasks).hashTag(hashTag).build());
        }

        tasks.update(taskRequestDto.getTitle(), taskRequestDto.getContent(), taskTags);

        return id;
    }

    @Override
    @Transactional(readOnly = true)
    public List<TaskDto.Response> getTaskListByMemberId(Long memberId){
        return taskRepository.findTaskListByMemberId(memberId).stream()
                .map(TaskDto.Response::of)
                .collect(Collectors.toList());
    }



}