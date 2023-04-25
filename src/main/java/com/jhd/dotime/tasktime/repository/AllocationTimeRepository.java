package com.jhd.dotime.tasktime.repository;

import com.jhd.dotime.tasks.entity.Task;
import com.jhd.dotime.tasktime.entity.AllocationTime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AllocationTimeRepository extends JpaRepository<AllocationTime, Long> {
    Boolean existsByTaskAndType(Task task, String type);
}
