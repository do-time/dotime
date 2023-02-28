package com.jhd.dotime.tasks.common.exception;

import com.jhd.dotime.common.error.BaseErrorCode;
import com.jhd.dotime.tasks.common.error.TaskErrorCode;
import lombok.Getter;


@Getter
public class TaskException  extends RuntimeException{

    private final TaskErrorCode errorCode;

    public TaskException(TaskErrorCode errorCode, Throwable reason){
        super(errorCode.getMessage(), reason);
        this.errorCode = errorCode;
    }

    public TaskException(TaskErrorCode errorCode){
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }
}
