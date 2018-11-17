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
    private String SECRET;


    public String createToken(Student student, Date expDate) {
        String token = Jwts.builder()
                .setSubject("StudentService")
                .claim("userId", student.getId())
                .setExpiration(expDate)
                .signWith(SignatureAlgorithm.HS256, SECRET)
                .compact();
        return token;
    }

    public String getIdFromToken(String token) {
        Jws<Claims> claims = Jwts.parser()
                .setSigningKey(SECRET)
                .parseClaimsJws(token);
        String studentId = (String) claims.getBody().get("userId");
        return studentId;
    }
}
