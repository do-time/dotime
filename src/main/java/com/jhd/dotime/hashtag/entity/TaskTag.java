package com.jhd.dotime.hashtag.entity;


import com.jhd.dotime.tasks.entity.Task;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name="tasktag")
@Builder
public class TaskTag { // Task와 HashTag를 매핑하는 테이블
    @Id
    @Column(name="tasktag_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name="task_id")
    private Task task;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "hashtag_id")
    private HashTag hashTag;
}
