package com.example.dio.security.jwt;

import com.example.dio.config.AppEnv;
import com.example.dio.exception.InvalidJwtException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@AllArgsConstructor
public class JwtService {

    private final AppEnv appEnv;

    public String generateToken(TokenPayload tokenPayload){
        return Jwts.builder()
                .setClaims(tokenPayload.claims())
                .setIssuedAt(Date.from(tokenPayload.issuedAt()))
                .setExpiration(Date.from(tokenPayload.expiration()))
                .signWith(KeyHolder.getKey(appEnv.getSecurity().getSecret()), SignatureAlgorithm.HS256)
                .compact();
    }

    public Claims parseToken(String token){
       try{
           return Jwts.parserBuilder()
                   .setSigningKey(KeyHolder.getKey(token))
                   .build()
                   .parseClaimsJws(token)
                   .getBody();
       }
       catch(JwtException | IllegalArgumentException e){
           throw new InvalidJwtException("invalid Failed to pass Token , Invalid Jwt");
       }
    }
}
