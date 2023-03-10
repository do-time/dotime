package com.jhd.dotime.auth;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException {
        //필요한 권한이 없이 접근하려 할때 403
        response.sendError(HttpServletResponse.SC_FORBIDDEN);
    }
//    static String _403 = "{\"success\":false,\"response\":null,\"error\":{\"message\":\"Forbidden\",\"status\":403}}";
//
//    @Override
//    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException {
//        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
//        response.setHeader("content-type", "application/json");
//        response.getWriter().write(_403);
//        response.getWriter().flush();
//        response.getWriter().close();
//    }

}