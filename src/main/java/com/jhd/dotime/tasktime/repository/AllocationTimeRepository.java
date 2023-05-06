package com.jhd.dotime.tasktime.repository;

import com.jhd.dotime.tasktime.dto.AllocationCategory;
import com.jhd.dotime.tasktime.entity.AllocationTime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AllocationTimeRepository extends JpaRepository<AllocationTime, Long> {
    Boolean existsByTaskIdAndCategory(Long taskId, AllocationCategory category);

    Optional<AllocationTime> findByTaskIdAndCategory(Long TaskId, AllocationCategory category);

    List<AllocationTime> findAllByTaskId(Long TaskId);
}
