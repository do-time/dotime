package com.jhd.dotime.tasks.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;


@Getter
@Setter
@RequiredArgsConstructor
//@NoArgsConstructor
@Entity
public class Task {
    @Id
    private Long id;
    private String title;

    private String content;
    private LocalDateTime create_date;
    private LocalDateTime updated_date;

    private Long member_id;


    public Task(Task task){
        this.id = task.getId();
        this.title = task.getTitle();
        this.content = task.getContent();
        this.create_date = task.getCreate_date();
        this.updated_date = task.getUpdated_date();
        this.member_id = task.getMember_id();
    }

    public Task(Long id, String title, LocalDateTime created_date, LocalDateTime updated_date) {
        this.id = id;
        this.title = title;
        this.create_date = created_date;
        this.updated_date = updated_date;
    }


}
