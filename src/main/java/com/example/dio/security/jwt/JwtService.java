package com.example.dio.security.jwt;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtService {

    private final String secrate="+qu9DMSQOLYnM5zSxRdQ3nJAm6OXBaHnYERnIpdhRtU=";
    private final Key key;

    {
        this.key = generateKey();
    }

    public Key generateKey(){
       return Keys.hmacShaKeyFor(Decoders.BASE64.decode(secrate));
    }

    public String generateToken(TokenPayload tokenPayload){
        return Jwts.builder()
                .setClaims(tokenPayload.claims())
                .setIssuedAt(new Date(tokenPayload.issuedAt()))
                .setExpiration(new Date(tokenPayload.expiration()))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }
}
