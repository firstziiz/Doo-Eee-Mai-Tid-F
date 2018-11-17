package com.SubjectService.Interceptor;

import com.SubjectService.Exception.BadRequestException;
import com.SubjectService.Exception.JWTException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class TokenInterceptor implements HandlerInterceptor {
    private String SECRET;

    public TokenInterceptor(String secret) {
        this.SECRET = secret;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = getToken(request);
        String userId = this.getUserIdFromToken(token);
        request.setAttribute("userId", userId);
        return true;
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
        if (this.isValidToken(token)) {
            throw new BadRequestException("Invalid authorization provided.");
        }
        return token;
    }
    
    private String getUserIdFromToken(String token) throws JWTException {
        String userId;
        try {
            Jws<Claims> claims = Jwts.parser()
                    .setSigningKey(this.SECRET)
                    .parseClaimsJws(token);
            userId = (String) claims.getBody().get("userId");
        } catch (Exception jwtException) {
            throw new JWTException(jwtException.getMessage());
        }
        return userId;
    }
}
