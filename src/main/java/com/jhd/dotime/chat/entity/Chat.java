package com.jhd.dotime.chat.entity;

import com.jhd.dotime.common.entity.BaseTimeEntity;
import com.jhd.dotime.members.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity(name = "chat")
public class Chat extends BaseTimeEntity {
    @Id
    @Column(name="chat_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "content")
    private String content;


    @Column(name="sender")
    @JoinColumn(name = "member_id")
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Member sender;

    /**
     * receiver를 MANYTOONE으로 할지 아니면 socket 자체에서 해결을 해주는지 알아봐야함.
     */
    @Column(name="receiver")
    @JoinColumn(name = "member_id")
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Member receiver;
}
