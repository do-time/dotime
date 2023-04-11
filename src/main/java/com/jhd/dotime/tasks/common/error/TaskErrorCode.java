package com.jhd.dotime.tasks.common.error;


import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.*;

@Getter
@AllArgsConstructor
public enum TaskErrorCode {

    /**
     * 400 - BAD_REQUEST
     * 1. task 입력값 오류
     */

    INPUT_VALUE_ERROR(BAD_REQUEST, "잘못된 입력 값 입니다."),


    /**
     * 404 - NOTFOUND
     */
    TASK_NOT_FOUNT(NOT_FOUND, "Task가 존재하지 않습니다."),

    /**
     * 409 - CONFLICT
     */
    DUPLICATE_TASK_RESOURCE(CONFLICT, "데이터가 이미 존재합니다."),

    ;

    private final HttpStatus status;
    private final String message;


}
