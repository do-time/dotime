package com.jhd.dotime.hashtag.service;

import com.jhd.dotime.hashtag.entity.HashTag;
import com.jhd.dotime.hashtag.entity.TaskTag;
import com.jhd.dotime.hashtag.repository.HashTagRepository;
import com.jhd.dotime.hashtag.repository.TaskTagRepository;
import com.jhd.dotime.members.common.exception.NotFoundException;
import com.jhd.dotime.tasks.common.error.TaskErrorCode;
import com.jhd.dotime.tasks.common.exception.TaskException;
import com.jhd.dotime.tasks.entity.Task;
import com.jhd.dotime.tasks.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
public class TaskTagServiceImpl implements TaskTagService{
    private final TaskTagRepository taskTagRepository;

    private final TaskRepository taskRepository;

    private final HashTagRepository hashTagRepository;

    @Override
    public Long createTaskTag(Long taskId, List<Long> hashtagIdLst) {

        Task task = taskRepository.findById(taskId).orElseThrow(() -> new TaskException(TaskErrorCode.TASK_NOT_FOUNT));;
        for (Long hashtagId : hashtagIdLst) {
            HashTag hashTag = hashTagRepository.findById(hashtagId).orElseThrow(() -> new NotFoundException("해시태그가 존재하지 않습니다."));

            taskTagRepository.save(TaskTag.builder().task(task).hashTag(hashTag).build());
        }

        return taskId;
    }

    @Override
    public List<HashTag> getHashTagList(Long taskId) {
        List<HashTag> hashTagList = new ArrayList<>();
        List<TaskTag> taskTagList = taskTagRepository.findTaskTagByTaskId(taskId);
        for (TaskTag taskTag : taskTagList) {
            hashTagList.add(taskTag.getHashTag());
        }



        return hashTagList;
    }
}
