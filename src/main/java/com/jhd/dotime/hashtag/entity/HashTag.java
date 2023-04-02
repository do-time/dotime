package com.jhd.dotime.hashtag.entity;


import com.jhd.dotime.common.entity.BaseTimeEntity;
import com.jhd.dotime.members.entity.Member;
import lombok.*;

import javax.persistence.*;
import java.util.Objects;


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

    @Override
    public boolean equals(Object o){
        if(this == o)
            return true;

        if(!(o instanceof HashTag))
            return false;

        HashTag hashTag = (HashTag) o;

        return hashTag.id == id &&
                hashTag.name.equals(name);
    }

    @Override
    public int hashCode(){
        return Objects.hash(id, name);
    }
}
