package com.jhd.dotime.common.exception;

import com.jhd.dotime.common.error.BaseErrorCode;
import com.jhd.dotime.common.error.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@Component
public class JwtExceptionHandlerFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            filterChain.doFilter(request, response);
        } catch (JwtInvalidException e) {
            log.error("handleCustomException throw CustomException : {}", e.getErrorCode());
            setErrorResponse(HttpStatus.UNAUTHORIZED, request, response, e.getErrorCode());
        }
    }

    public void setErrorResponse(HttpStatus status, HttpServletRequest request,
                                 HttpServletResponse response, BaseErrorCode errorCode) throws IOException {

        response.setStatus(status.value());
        response.setContentType("application/json; charset=UTF-8");

        response.getWriter().write(
                ErrorResponse.of(errorCode)
                        .convertToJson()
        );
    }
}
