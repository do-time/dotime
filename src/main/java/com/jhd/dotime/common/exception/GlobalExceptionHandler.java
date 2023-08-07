package com.jhd.dotime.common.exception;

import com.jhd.dotime.common.error.ErrorCode;
import com.jhd.dotime.common.error.ErrorResponse;
import com.jhd.dotime.members.common.error.MemberErrorCode;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.jhd.dotime.common.error.ErrorCode.DUPLICATE_RESOURCE;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value={ ConstraintViolationException.class, DataIntegrityViolationException.class})
    protected ResponseEntity<ErrorResponse> handleDataException(){
        log.error("handleDataException throw Exception : {}", DUPLICATE_RESOURCE);
        return ErrorResponse.toResponseEntity(DUPLICATE_RESOURCE);
    }

    @ExceptionHandler(value = { CustomException.class})
    protected ResponseEntity<ErrorResponse> handleCustomException(CustomException e){
        log.error("handleCustomException throw CustomException : {}", e.getErrorCode());
        return ErrorResponse.toResponseEntity(e.getErrorCode());
    }

    /**
     *
     * handleMethodArgumentNotValid
     *
     * @author : jihoon
     * @date : 2023/07/30
     * @version 1.0.0
     * @description : @Valiation 에 걸리는 경우 사용되는 예외처리
     *                400 BAD_REQUEST와 @Validatino의 message 의 값이 반환된다.
     *
    **/
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<String> messages = ex.getBindingResult().getFieldErrors()
                .stream()
                .map(e -> e.getDefaultMessage())
                .collect(Collectors.toList());

        log.error("handleMethodArgumentNotValid throw ValidateException : {}", status.BAD_REQUEST);

        Map<String, Object> body = ErrorResponse.toBody(ErrorCode.INVALID_INPUT_VALUE, messages);

        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);

    }}
