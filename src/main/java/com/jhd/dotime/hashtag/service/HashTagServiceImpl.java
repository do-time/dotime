package com.jhd.dotime.hashtag.service;


import com.jhd.dotime.common.exception.CustomException;
import com.jhd.dotime.hashtag.dto.HashTagRequestDto;
import com.jhd.dotime.hashtag.entity.HashTag;
import com.jhd.dotime.hashtag.entity.TaskTag;
import com.jhd.dotime.hashtag.repository.HashTagRepository;
import com.jhd.dotime.hashtag.repository.TaskTagRepository;
import com.jhd.dotime.members.common.error.MemberErrorCode;
import com.jhd.dotime.members.entity.Member;
import com.jhd.dotime.members.repository.MemberRepository;
import com.jhd.dotime.tasks.common.error.TaskErrorCode;
import com.jhd.dotime.tasks.common.exception.TaskException;
import com.jhd.dotime.tasks.entity.Task;
import com.jhd.dotime.tasks.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HashTagServiceImpl implements HashTagService {

    private final TaskRepository taskRepository;

    private final TaskTagRepository taskTagRepository;

    private final HashTagRepository hashTagRepository;

    @Override
    @Transactional
    public List<HashTag> getHashTagList() {
        return hashTagRepository.findAll();
    }

    @Override
    @Transactional
    public Long createHashtag(Long taskId, HashTagRequestDto hashTagRequestDto) {
        Task task = taskRepository.findById(taskId).orElseThrow(() -> new TaskException(TaskErrorCode.TASK_NOT_FOUNT));
        TaskTag newTaskTag = TaskTag.builder().task(task).hashTag(hashTagRequestDto.toEntity()).build();
        taskTagRepository.save(newTaskTag);

        return newTaskTag.getId();

    }
}
