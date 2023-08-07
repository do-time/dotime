package com.jhd.dotime.common.error;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.*;


@Getter
@AllArgsConstructor
public enum ErrorCode implements BaseErrorCode {


    /**
     * 400 BAD_REQUEST
     * 잘못된 요청
     */
    INVALID_INPUT_VALUE(BAD_REQUEST, "입력된 값이 맞지 않는 형식입니다."),
    CANNOT_CREATE_SAME_TASK(BAD_REQUEST, "같은 제목의 TO-DO를 생성할 수 없습니다."),

    /**
     * 401 UNAUTHORIZED
     * 인증되지 않은 사용자
     */
    INVALID_AUTH_TOKEN(UNAUTHORIZED, "권한 정보가 없는 토큰입니다."),
    EXPIRED_AUTH_TOKEN(UNAUTHORIZED, "만료된 JWT 토큰입니다."),
    UNSUPPORTED_AUTH_TOKEN(UNAUTHORIZED, "지원되지 않는 JWT 토큰입니다."),
    ILLEGAL_AUTH_TOKEN(UNAUTHORIZED, "JWT 토큰이 잘못되었습니다."),

    /**
     * 404 NOT_FOUND
     * RESOURCE를 찾을 수 없음
     */
    MEMBER_NOT_FOUND(NOT_FOUND, "해당 유저를 찾을 수 없습니다."),


    /**
     * 409 CONFLICT
     * RESOURCE의 현재 상태와 충돌, 중복된 데이터 존재
     * 1. MEMBER EMAIL REGEX에 걸릴경우
     * 2. TASK의 같은 TITLE로 등록할 경우
     */
    DUPLICATE_RESOURCE(CONFLICT, "데이터가 이미 존재합니다.");

    ;

    private final HttpStatus status;
    private final String message;

}
