package com.jhd.dotime.chat.common.error;

import com.jhd.dotime.common.error.BaseErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.NOT_FOUND;


@Getter
@AllArgsConstructor
public enum ChatRoomErrorCode implements BaseErrorCode {

    /**
     * 400 BAD_REQUEST
     * 잘못된 요청
     */


    /**
     * 401 UNAUTHORIZED
     * 인증되지 않은 사용자
     */


    /**
     * 404 NOT_FOUND
     * RESOURCE를 찾을 수 없음
     */
    CHATROOM_NOT_FOUND(NOT_FOUND, "해당 방을 찾을 수 없습니다."),

    /**
     * 409 CONFLICT
     * RESOURCE의 현재 상태와 충돌, 중복된 데이터 존재
     */


    ;

    private final HttpStatus status;
    private final String message;
}