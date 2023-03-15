package com.jhd.dotime.hashtag.service;

import com.jhd.dotime.hashtag.entity.HashTag;
import com.jhd.dotime.hashtag.entity.TaskTag;
import com.jhd.dotime.hashtag.repository.TaskTagRepository;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
public class TaskTagServiceImpl implements TaskTagService{
    private final TaskTagRepository taskTagRepository;

    @Override
    public Long createTaskTag(Long memberId, Long taskId) {
        return null;
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
