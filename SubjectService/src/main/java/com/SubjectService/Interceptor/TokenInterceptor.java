package com.SubjectService.Interceptor;

import com.SubjectService.Exception.ClientErrorException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class TokenInterceptor implements HandlerInterceptor {
    private String secret;

    public TokenInterceptor(String secret) {
        this.secret = secret;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String userId;
        String token = request.getHeader("Authorization");
        if (!token.startsWith("Bearer")) {
            throw new ClientErrorException("No bearer header provided");
        }
        if (token.equals("")) {
            throw new ClientErrorException("No Authorization header provided");
        }
        try {
            Jws<Claims> claims = Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token);
            userId = (String) claims.getBody().get("userId");
        } catch (Exception jwtException) {
            throw new ClientErrorException(jwtException.getMessage());
        }
        request.setAttribute("userId", userId);
        return true;
    }
}
