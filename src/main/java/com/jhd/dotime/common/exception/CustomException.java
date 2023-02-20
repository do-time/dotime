package com.jhd.dotime.common.exception;


import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class CustomException extends RuntimeException{


    private final String message;
    private final HttpStatus status;


    public CustomException(String message, HttpStatus status, Throwable reason) {
        super(message, reason);
        this.status = status;
        this.message = message;
    }

    public CustomException(String message, HttpStatus status) {
        super(message);
        this.status = status;
        this.message = message;
    }
}
