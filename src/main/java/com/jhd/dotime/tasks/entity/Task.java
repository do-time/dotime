package com.jhd.dotime.tasks.entity;

import com.jhd.dotime.common.entity.BaseTimeEntity;
import com.jhd.dotime.members.entity.Member;
import lombok.*;
import javax.persistence.*;


@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name="task")
public class Task extends BaseTimeEntity {
    @Id
    @Column(name="task_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "member_id")
    private Member member;

    @Column
    private String title;

    @Column
    private String content;

    @Column
    private String hashtag;



    @Builder
    public Task(Member member, String title, String content, String hashtag){
        this.member = member;
        this.title = title;
        this.content = content;
        this.hashtag = hashtag;
    }



    public void update(String title, String content){
        this.title = title;
        this.content = content;
    }



}
