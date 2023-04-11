package com.jhd.dotime.chat.entity;

import com.jhd.dotime.common.entity.BaseTimeEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity(name="chatroom")
public class ChatRoom extends BaseTimeEntity {

    @Id
    @Column(name="chatroom_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name="people_count")
    private Long people_count;

    @Builder
    public ChatRoom(String title, Long people_count){
        this.title = title;
        this.people_count = people_count;
    }


}
