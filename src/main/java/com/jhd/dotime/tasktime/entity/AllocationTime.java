package com.jhd.dotime.tasktime.entity;

import com.jhd.dotime.common.entity.BaseTimeEntity;
import com.jhd.dotime.tasks.entity.Task;
import com.jhd.dotime.tasktime.dto.AllocationCategory;
import lombok.*;

import javax.persistence.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name="allocation_time")
@ToString
public class AllocationTime extends BaseTimeEntity {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    @Enumerated(EnumType.STRING)
    private AllocationCategory category;

    @Column Long hour;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "task_id")
    private Task task;

    @Builder
    public AllocationTime(AllocationCategory category, Long hour, Task task){
        this.category = category;
        this.hour = hour;
        this.task = task;
    }

    public void updateTime(Long hour){
        this.hour = hour;
    }
}
