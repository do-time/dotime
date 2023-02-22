package com.jhd.dotime.common.exception;


import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum CommonExceptionContext {;

    private final String message;
    private final HttpStatus status;

    CommonExceptionContext(String message, HttpStatus status){
        this.message = message;
        this.status = status;
    }

    public CustomException exception(){
        return new CustomException(this.message, this.status);
    }

    public CustomException exception(Throwable reason){
        return new CustomException(this.message, this.status, reason);
    }


}
