package com.jhd.dotime.tasktime.common.exception;

import com.jhd.dotime.tasktime.common.error.TaskTimeLogErrorCode;
import lombok.Getter;


@Getter
public class TaskTimeLogException extends RuntimeException{

    private final TaskTimeLogErrorCode errorCode;

    public TaskTimeLogException(TaskTimeLogErrorCode errorCode, Throwable reason){
        super(errorCode.getMessage(), reason);
        this.errorCode = errorCode;
    }

    public TaskTimeLogException(TaskTimeLogErrorCode errorCode){
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }
}
