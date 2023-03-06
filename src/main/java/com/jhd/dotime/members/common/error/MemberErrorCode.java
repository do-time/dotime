package com.jhd.dotime.members.common.error;

import com.jhd.dotime.common.error.BaseErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.*;

@Getter
@AllArgsConstructor
public enum MemberErrorCode implements BaseErrorCode {

    /**
     * 400 BAD_REQUEST
     * 잘못된 요청
     */
    INVALID_EMAIL(BAD_REQUEST, "잘못된 형식의 이메일입니다."),

    /**
     * 401 UNAUTHORIZED
     * 인증되지 않은 사용자
     */
    INVALID_AUTH(UNAUTHORIZED, "접근 권한이 없습니다."),

    /**
     * 404 NOT_FOUND
     * RESOURCE를 찾을 수 없음
     */
    MEMBER_NOT_FOUND(NOT_FOUND, "해당 유저를 찾을 수 없습니다."),

    /**
     * 409 CONFLICT
     * RESOURCE의 현재 상태와 충돌, 중복된 데이터 존재
     */
    DUPLICATE_EMAIL(CONFLICT, "중복된 이메일입니다.");

    ;
    private final HttpStatus status;
    private final String message;
}

