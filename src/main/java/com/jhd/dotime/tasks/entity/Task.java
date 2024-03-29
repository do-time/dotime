package com.jhd.dotime.tasks.entity;

import com.jhd.dotime.common.entity.BaseTimeEntity;
import com.jhd.dotime.hashtag.entity.HashTag;
import com.jhd.dotime.hashtag.entity.TaskTag;
import com.jhd.dotime.members.entity.Member;
import com.jhd.dotime.tasktime.entity.AllocationTime;
import io.swagger.annotations.ApiModel;
import lombok.*;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name="task")
@ApiModel(value = "task")
public class Task extends BaseTimeEntity {
    @Id
    @Column(name="task_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "member_id")
    private Member member;

    @Column
    private String title;

    @Column
    private String content;


    @OneToMany(mappedBy = "task", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    List<TaskTag> taskTagList = new ArrayList<>();

    @OneToMany(mappedBy = "task", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    List<AllocationTime> allocationTimeList = new ArrayList<>();

    @Builder
    public Task(Member member, String title, String content){
        this.member = member;
        this.title = title;
        this.content = content;
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
