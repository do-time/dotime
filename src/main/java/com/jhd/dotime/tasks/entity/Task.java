package com.jhd.dotime.tasks.entity;

import com.jhd.dotime.common.entity.BaseTimeEntity;
import com.jhd.dotime.hashtag.entity.HashTag;
import com.jhd.dotime.hashtag.entity.TaskTag;
import com.jhd.dotime.members.entity.Member;
import lombok.*;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


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


    @OneToMany(mappedBy = "task", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    List<TaskTag> taskTagList = new ArrayList<>();


    @Builder
    public Task(Member member, String title, String content, String hashtag){
        this.member = member;
        this.title = title;
        this.content = content;
        this.hashtag = hashtag;
    }

    public List<HashTag> getHashTag(){
        return taskTagList.stream().map(TaskTag::getHashTag)
                .collect(Collectors.toList());
    }

    public void update(String title, String content, List<TaskTag> taskTagList){
        this.title = title;
        this.content = content;
        this.taskTagList = taskTagList;
    }



}
