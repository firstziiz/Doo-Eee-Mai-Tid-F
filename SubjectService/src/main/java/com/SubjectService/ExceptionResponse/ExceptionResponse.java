package com.SubjectService.ExceptionResponse;

import com.SubjectService.Logger.AuditLogger;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ExceptionResponse {
    private static AuditLogger logger = new AuditLogger(ExceptionResponse.class.getName());

    public static void involveResponseWithException(HttpServletRequest request, HttpServletResponse response, Exception exception, HttpStatus httpStatus) {
        ObjectMapper objectMapper = new ObjectMapper();
        ExceptionResponseBody exceptionResponseBody = new ExceptionResponseBody(
                httpStatus.value(),
                httpStatus.getReasonPhrase(),
                exception.getMessage(),
                request.getRequestURI()
        );
        response.setStatus(httpStatus.value());
        try {
            String responseBody = objectMapper.writeValueAsString(exceptionResponseBody);
            response.getWriter().write(responseBody);
        } catch (Exception ex) {
            logger.error(request, ex.getMessage());
        }
    }
}
