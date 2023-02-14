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
    @Column
    private String title;
    @Column
    private String content;

    @Builder
    public Task(String title, String content){
        this.title = title;
        this.content = content;
    }


    public void update(String title, String content){
        this.title = title;
        this.content = content;
    }


}
