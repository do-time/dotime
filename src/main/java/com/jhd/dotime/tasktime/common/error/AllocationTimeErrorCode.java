package com.jhd.dotime.tasktime.common.error;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.*;

@Getter
@AllArgsConstructor
public enum AllocationTimeErrorCode {

    /**
     * 400 - BAD_REQUEST
     * 1. task 입력값 오류
     */

    INPUT_VALUE_ERROR(BAD_REQUEST, "잘못된 입력 값 입니다."),


    /**
     * 404 - NOTFOUND
     */
    ALLOCATION_TIME_NOT_FOUNT(NOT_FOUND, "등록된 할당 시간이 없습니다."),

    /**
     * 409 - CONFLICT
     */
    ALLOCATION_TIME_DUPLICATE(CONFLICT, "해당 할당 시간이 이미 존재합니다.")
    ;

    private final HttpStatus status;
    private final String message;
}
