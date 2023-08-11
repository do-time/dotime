package com.jhd.dotime.common.exception;

import com.jhd.dotime.common.error.BaseErrorCode;
import io.jsonwebtoken.JwtException;
import lombok.Getter;

@Getter
public class JwtInvalidException extends JwtException {
    private final BaseErrorCode errorCode;


    public JwtInvalidException(BaseErrorCode errorCode, Throwable reason) {
        super(errorCode.getMessage(), reason);
        this.errorCode = errorCode;
    }

    public JwtInvalidException(BaseErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }
}
