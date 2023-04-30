package com.jhd.dotime.chat.entity;

import com.jhd.dotime.common.entity.BaseTimeEntity;
import com.jhd.dotime.members.entity.Member;
import lombok.*;
import javax.persistence.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "chatmessage")
public class ChatMessage extends BaseTimeEntity {
    @Id
    @Column(name="chat_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "content")
    private String content;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "room_id",insertable = false, updatable=false)
    private ChatRoom chatRoom;


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
    public ChatMessage(String content, Member sender){
        this.content = content;
        this.sender = sender;

    }
}
