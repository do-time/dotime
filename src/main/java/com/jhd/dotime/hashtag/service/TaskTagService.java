package com.jhd.dotime.hashtag.service;

import com.jhd.dotime.hashtag.entity.HashTag;
import com.jhd.dotime.hashtag.entity.TaskTag;

import java.util.List;

public interface TaskTagService {

    Long createTaskTag(Long taskId, List<Long> hashtagIdLst);

    List<HashTag> getHashTagList(Long taskId);
}
