package com.sit.cloudnative.AuthenticationService;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.security.Security;
import java.util.Date;

import com.sit.cloudnative.AuthenticationService.Exception.JwtExpiredException;
import com.sit.cloudnative.AuthenticationService.Exception.TokenNotFoundException;

@Service
public class TokenService {
    @Value("${jwt.secret}")
    private String SECRET_KEY;

    @Value("${jwt.expiresec}")
    private long EXPIRE_IN_SECOND;

    private final int MILLISECOND = 1000;

    private long getExpireMillisecond() {
        return System.currentTimeMillis() + EXPIRE_IN_SECOND * MILLISECOND;
    }

    public AuthResponse createToken(User user) {
        AuthResponse authResponse = new AuthResponse();
        Date expiredDate = new Date(this.getExpireMillisecond());
        String token = Jwts.builder().setSubject("UserService").claim("userId", user.getId())
                .claim("role", user.getRole()).setExpiration(expiredDate).signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
        authResponse.setToken(token);
        authResponse.setExpiryDate(expiredDate.getTime());
        return authResponse;
    }

    public String getIdFromToken(String token) {
        if (token.length() < 7) {
            throw new TokenNotFoundException("Token is empty.");
        }
        String tokenFormat = token.substring(7);
        try {
            Jws<Claims> claims = Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(tokenFormat);
            String userId = (String) claims.getBody().get("userId");
            return userId;
        } catch (ExpiredJwtException e) {
            throw new JwtExpiredException(String.format("Token Expired for %s.", e.getClaims().toString()),
                    e.getCause());
        }
    }
}
