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
    @JoinColumn(name="hashtag_id")
    private Long hashtag_id;
}
