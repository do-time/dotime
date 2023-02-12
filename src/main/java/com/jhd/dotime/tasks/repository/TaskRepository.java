package com.jhd.dotime.tasks.repository;

import com.jhd.dotime.tasks.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.HashMap;

public interface TaskRepository extends JpaRepository<Task, Long> {

}
