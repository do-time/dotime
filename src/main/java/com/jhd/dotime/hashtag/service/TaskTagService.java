package com.jhd.dotime.hashtag.service;

import com.jhd.dotime.hashtag.entity.HashTag;
import com.jhd.dotime.hashtag.entity.TaskTag;

import java.util.List;

public interface TaskTagService {

    Long createTaskTag(Long memberId, Long taskId);

    List<HashTag> getHashTagList(Long taskId);
}
