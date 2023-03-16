package com.jhd.dotime.hashtag.dto;


import com.jhd.dotime.hashtag.entity.HashTag;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;



@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HashTagRequestDto {
    private String name;


    public HashTag toEntity(){
        return HashTag.builder()
                .name(name)
                .build();
    }
}
