package com.jhd.dotime.chat.entity;

import com.jhd.dotime.common.entity.BaseTimeEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.socket.WebSocketSession;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;


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

//    @Column(name = "sessions")
//    @Builder.Default
//    private Set<WebSocketSession> sessions = new HashSet<>();

    @Builder
    public ChatRoom(String title, Long people_count){
        this.title = title;
        this.people_count = people_count;
    }


}
