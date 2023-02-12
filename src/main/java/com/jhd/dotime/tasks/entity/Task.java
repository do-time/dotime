package com.jhd.dotime.tasks.entity;

import lombok.*;

import javax.annotation.Nullable;
import javax.persistence.*;
import java.time.LocalDateTime;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String title;
    @Column
    private String content;
    @Column
    private LocalDateTime create_date;
    @Column
    private LocalDateTime updated_date;

//    @Column
//    private Long member_id;


//    public Task(Task task){
//        this.id = task.getId();
//        this.title = task.getTitle();
//        this.content = task.getContent();
//        this.create_date = task.getCreate_date();
//        this.updated_date = task.getUpdated_date();
////        this.member_id = task.getMember_id();
//    }

    @Builder
    public Task(String title, String content, LocalDateTime created_date, LocalDateTime updated_date) {
        this.title = title;
        this.content = content;
        this.create_date = created_date;
        this.updated_date = updated_date;
    }


}
