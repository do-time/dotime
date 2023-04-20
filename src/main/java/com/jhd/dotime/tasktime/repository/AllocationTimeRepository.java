package com.jhd.dotime.tasktime.repository;

import com.jhd.dotime.tasktime.entity.AllocationTime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AllocationTimeRepository extends JpaRepository<AllocationTime, Long> {
}
