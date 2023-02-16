package com.jhd.dotime.tasks.entity;

import com.jhd.dotime.common.entity.BaseTimeEntity;
import lombok.*;
import javax.persistence.*;


@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name="task")
public class Task extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="member_id")
    private Long memberId;
    @Column
    private String title;
    @Column
    private String content;

    @Builder
    public Task(Long memberId, String title, String content){
        this.memberId = memberId;
        this.title = title;
        this.content = content;
    }


    public void update(String title, String content){
        this.title = title;
        this.content = content;
    }


}
