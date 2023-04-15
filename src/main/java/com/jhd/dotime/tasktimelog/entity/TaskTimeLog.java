package com.jhd.dotime.tasktimelog.entity;

import com.jhd.dotime.members.entity.Member;
import com.jhd.dotime.tasks.entity.Task;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@Entity(name="taskTimeLog")
public class TaskTimeLog {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "task_id")
    private Task task;

    @CreatedDate
    private LocalDateTime startTime;

    @Column
    private LocalDateTime endTime;

    @Builder
    public TaskTimeLog(Long id, Task task) {
        this.id = id;
        this.task = task;
    }
}
