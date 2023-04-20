package com.jhd.dotime.chat.entity;

import com.jhd.dotime.common.entity.BaseTimeEntity;
import com.jhd.dotime.members.entity.Member;
import lombok.*;
import org.springframework.web.socket.WebSocketSession;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "chat")
public class Chat extends BaseTimeEntity {
    @Id
    @Column(name="chat_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "content")
    private String content;


    @JoinColumn(name = "room_id",insertable = false, updatable=false)
    private String roomId;



    @JoinColumn(name = "member_id",insertable = false, updatable=false)
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    private Member sender;

    /**
     * receiver를 MANYTOONE으로 할지 아니면 socket 자체에서 해결을 해주는지 알아봐야함.
     */
//    @JoinColumn(name = "member_id",insertable = false, updatable=false)
//    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
//    private Member receiver;


    @Builder
    public Chat(String content, Member sender){
        this.content = content;
        this.sender = sender;


    }
}
