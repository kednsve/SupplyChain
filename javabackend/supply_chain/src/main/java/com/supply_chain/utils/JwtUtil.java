package com.supply_chain.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtUtil {
    private static final String JWT_SECRET = "JWT_SECRET";
    private static final SecretKey JWT_KEY=Jwts.SIG.HS256.key().build();
    private static final long JWT_EXPIRATION_TIME = 1000 * 60 * 10;

    public String generateToken(Integer userId) {
        Date now = new Date();
        Date expireDate = new Date(now.getTime() + JWT_EXPIRATION_TIME);
        Map<String, Object> claims = new HashMap<>();
        claims.put("userId", userId);
        return Jwts
                .builder()
                .signWith(JWT_KEY)
                .claims(claims)
                .expiration(expireDate)
                .compact();
    }
    public Claims validateToken(String token) {
        return Jwts.parser().verifyWith(JWT_KEY).build().parseSignedClaims(token).getPayload();
    }
}
