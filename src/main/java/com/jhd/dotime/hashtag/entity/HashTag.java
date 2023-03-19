package com.jhd.dotime.hashtag.entity;


import com.jhd.dotime.common.entity.BaseTimeEntity;
import lombok.*;

import javax.persistence.*;


@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name="hashtag")
@Builder
public class HashTag extends BaseTimeEntity { // 실제 HashTag를 저장하는 엔티티

    @Id
    @Column(name="hashtag_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;


}
