package com.example.dio.security.jwt;

import com.example.dio.config.AppEnv;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.security.Key;
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
}
