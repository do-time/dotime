package com.jhd.dotime.chat.entity.constant;


import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@RequiredArgsConstructor
public enum MessageType {
    TEXT("TEXT", "텍스트"),
    IMAGE("IMAGE", "이미지"),
    ALERT("ALERT", "알림");

    private final String key;
    private final String value;

}
