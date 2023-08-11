package com.jhd.dotime.common.error;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.AbstractMap;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
@Builder
public class ErrorResponse {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    private final LocalDateTime timestamp = LocalDateTime.now();
    private final int status;
    private final String error;
    private final String code;
    private final String message;


    public static ResponseEntity<ErrorResponse> toResponseEntity(BaseErrorCode errorCode) {
        return ResponseEntity
                .status(errorCode.getStatus())
                .body(
                        ErrorResponse.of(errorCode)
                );
    }

    public static ErrorResponse of(BaseErrorCode errorCode){
        return ErrorResponse.builder()
                    .status(errorCode.getStatus().value())
                    .error(errorCode.getStatus().name())
                    .code(errorCode.name())
                    .message(errorCode.getMessage())
                    .build();
    }

    public static Map<String, Object> toBody(BaseErrorCode errorCode, List<String> messages){
        return Map.ofEntries(
                new AbstractMap.SimpleEntry<>("status", errorCode.getStatus().value()),
                new AbstractMap.SimpleEntry<>("error", errorCode.getStatus().name()),
                new AbstractMap.SimpleEntry<>("code", errorCode.name()),
                new AbstractMap.SimpleEntry<>("message", messages)
        );
    }

    public String convertToJson() throws JsonProcessingException {
        return objectMapper.writeValueAsString(this);
    }
}
