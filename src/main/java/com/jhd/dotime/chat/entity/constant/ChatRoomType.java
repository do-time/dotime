package com.jhd.dotime.chat.entity.constant;


import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ChatRoomType {
    GROUP("GROUP", "그룹");


    private final String key;
    private final String value;
}
