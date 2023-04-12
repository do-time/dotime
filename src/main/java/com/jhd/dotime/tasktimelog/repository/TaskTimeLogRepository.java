package com.jhd.dotime.tasktimelog.repository;

import com.jhd.dotime.tasktimelog.entity.TaskTimeLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskTimeLogRepository extends JpaRepository<TaskTimeLog, Long> {
}
