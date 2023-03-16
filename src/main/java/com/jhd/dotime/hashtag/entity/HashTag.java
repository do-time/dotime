package com.jhd.dotime.hashtag.entity;


import com.jhd.dotime.common.entity.BaseTimeEntity;
import lombok.*;

import javax.persistence.*;


@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name="hashtag")
@Builder
public class HashTag extends BaseTimeEntity {

    @Id
    @Column(name="hashtag_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;


}
