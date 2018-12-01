package com.sit.cloudnative.MaterialService.Interceptor;

import com.sit.cloudnative.MaterialService.Exception.BadRequestException;
import com.sit.cloudnative.MaterialService.Exception.JWTException;
import com.sit.cloudnative.MaterialService.ExceptionResponse.ExceptionResponse;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sit.cloudnative.MaterialService.DTO.User;

public class TokenInterceptor implements HandlerInterceptor {
    private String SECRET;

    private ExceptionResponse exceptionResponse;

    public TokenInterceptor(String secret) {
        this.SECRET = secret;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (this.isOptionMethod(request)) {
            return true;
        }
        try {
            String token = getToken(request);
            String userId = this.getUserIdFromToken(token);
            request.setAttribute("userId", userId);
            return true;
        } catch (BadRequestException ex) {
            exceptionResponse.involveResponseWithException(request, response, ex, HttpStatus.BAD_REQUEST);
            return false;
        }
    }

    private boolean isValidToken (String token){
        if (token == null) {
            return false;
        } else if (token.startsWith("Bearer") == false || token.equals("")) {
            return false;
        }
        return true;
    }

    private String getToken (HttpServletRequest httpServletRequest) throws BadRequestException {
        String token = httpServletRequest.getHeader("Authorization");
        if (this.isValidToken(token) == false) {
            throw new BadRequestException("Invalid authorization provided: " + token);
        }
        return token;
    }

    private String getUserIdFromToken(String token) throws JWTException {
        String userId;
        String tokenFormat = token.substring(7);
        try {
            Jws<Claims> claims = Jwts.parser()
                    .setSigningKey(this.SECRET)
                    .parseClaimsJws(tokenFormat);
            userId = (String) claims.getBody().get("userId");
        } catch (Exception jwtException) {
            throw new JWTException(jwtException.getMessage());
        }
        return userId;
    }

    private boolean isOptionMethod(HttpServletRequest request) {
        return "OPTIONS".equalsIgnoreCase(request.getMethod());
    }
}