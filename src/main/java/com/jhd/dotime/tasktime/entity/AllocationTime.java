package com.jhd.dotime.tasktime.entity;

import com.jhd.dotime.common.entity.BaseTimeEntity;
import com.jhd.dotime.tasks.entity.Task;
import com.jhd.dotime.tasktime.dto.AllocationTimeDto;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name="allocationTime")
@ToString
public class AllocationTime extends BaseTimeEntity {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String type;

    @Column Long time;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "task_id")
    private Task task;

    @Builder
    public AllocationTime(String type, Long time, Task task){
        this.type = type;
        this.time = time;
        this.task = task;

    }
}
