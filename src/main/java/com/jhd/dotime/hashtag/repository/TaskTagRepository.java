package com.jhd.dotime.hashtag.repository;

import com.jhd.dotime.hashtag.entity.TaskTag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskTagRepository extends JpaRepository<TaskTag, Long> {

    List<TaskTag> findTaskTagByTaskId(Long taskId);

//    void deleteByMemberId
}
