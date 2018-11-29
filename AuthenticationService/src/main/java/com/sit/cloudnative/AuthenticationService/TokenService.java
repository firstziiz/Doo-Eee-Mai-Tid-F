package com.sit.cloudnative.AuthenticationService;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.security.Security;
import java.util.Date;

@Service
public class TokenService {
    @Value("${authenservice.jwt.secret}")
    private String SECRET_KEY;

    @Value("${authenservice.jwt.expiresec}")
    private long EXPIRE_IN_SECOND;

    private final int MILLISECOND= 1000;

    private long getExpireMillisecond() {
        return System.currentTimeMillis() + EXPIRE_IN_SECOND * MILLISECOND;
    }

    public AuthResponse createToken(Student student) {
        AuthResponse authResponse = new AuthResponse();
        Date expiredDate = new Date(this.getExpireMillisecond());
        String token = Jwts.builder()
                .setSubject("StudentService")
                .claim("userId", student.getId())
                .setExpiration(expiredDate)
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
        authResponse.setToken(token);
        authResponse.setExpiryDate(expiredDate.getTime());
        return authResponse;
    }

    public String getIdFromToken(String token) {
        String tokenFormat = token.substring(7);
        Jws<Claims> claims = Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(tokenFormat);
        String userId = (String) claims.getBody().get("userId");
        return userId;
    }
}
