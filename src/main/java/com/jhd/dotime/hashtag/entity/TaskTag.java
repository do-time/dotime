package com.jhd.dotime.hashtag.entity;


import javax.persistence.*;

public class TaskTag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="member_id")
    private Long member_id;

    @ManyToOne
    @JoinColumn(name="task_id")
    private Long task_id;
}
