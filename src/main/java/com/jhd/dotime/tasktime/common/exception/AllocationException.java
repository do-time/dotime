package com.jhd.dotime.tasktime.common.exception;

import com.jhd.dotime.tasktime.common.error.AllocationTimeErrorCode;
import com.jhd.dotime.tasktime.common.error.TaskTimeLogErrorCode;
import lombok.Getter;


@Getter
public class AllocationException extends RuntimeException{

    private final AllocationTimeErrorCode errorCode;

    public AllocationException(AllocationTimeErrorCode errorCode, Throwable reason){
        super(errorCode.getMessage(), reason);
        this.errorCode = errorCode;
    }

    public AllocationException(AllocationTimeErrorCode errorCode){
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }
}
