package com.jhd.dotime.common.exception;


import com.jhd.dotime.common.error.ErrorCode;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class CustomException extends RuntimeException{


//    private final String message;
//    private final HttpStatus status;
    private final ErrorCode errorCode;

    public CustomException(ErrorCode errorCode, Throwable reason){
        super(errorCode.getMessage(), reason);
        this.errorCode = errorCode;
    }

    public CustomException(ErrorCode errorCode){
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }

//    public CustomException(String message, HttpStatus status, Throwable reason) {
//        super(message, reason);
//        this.status = status;
//        this.message = message;
//    }
//
//    public CustomException(String message, HttpStatus status) {
//        super(message);
//        this.status = status;
//        this.message = message;
//    }
}