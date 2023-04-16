package com.jhd.dotime.tasktime.entity;

import com.jhd.dotime.tasks.entity.Task;
import com.jhd.dotime.tasktime.dto.TaskTimeLogDto;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name="taskTimeLog")
@ToString
public class TaskTimeLog {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "task_id")
    private Task task;

    @Column
    private LocalDateTime startTime;

    @Column
    private LocalDateTime endTime;

    @Builder
    public TaskTimeLog(Task task, LocalDateTime startTime, LocalDateTime endTime) {
        this.task = task;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public void update(LocalDateTime startTime, LocalDateTime endTime){
        this.startTime = startTime;
        this.endTime = endTime;
    }
}
