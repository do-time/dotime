package com.jhd.dotime.tasks.repository;

import com.jhd.dotime.tasks.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findTaskListByMemberId(Long memberId);

}
