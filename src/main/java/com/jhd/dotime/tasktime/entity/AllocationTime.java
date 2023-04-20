package com.jhd.dotime.tasktime.entity;

import com.jhd.dotime.common.entity.BaseTimeEntity;
import com.jhd.dotime.tasks.entity.Task;
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

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "task_id")
    private Task task;

    @Column
    private String type;

    @Column Long time;
}
