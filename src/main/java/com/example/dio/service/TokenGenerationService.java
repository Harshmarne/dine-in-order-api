package com.example.dio.service;

import com.example.dio.dto.request.AuthRecord;
import com.example.dio.security.jwt.ClaimName;
import com.example.dio.security.jwt.TokenPayload;
import com.example.dio.security.jwt.TokenType;
import com.example.dio.service.helper.TokenGeneratorServiceHelper;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Map;

@Service
@AllArgsConstructor
public class TokenGenerationService {

    private final TokenGeneratorServiceHelper tokenGeneratorServiceHelper;

    public HttpHeaders grantAccessToken(AuthRecord authRecord){
        Map<String,Object> newClaims = setClaims(authRecord);

        // Create new access token
        String newAccessToken = tokenGeneratorServiceHelper.generateToken(TokenType.ACCESS,newClaims,Instant.ofEpochMilli(authRecord.accessExperation()));

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.SET_COOKIE,newAccessToken);
        return headers;
    }

    public HttpHeaders grantAccessAndRefreshToken(AuthRecord authRecord){
        Map<String,Object> claims = setClaims(authRecord);
        String accessCookie = tokenGeneratorServiceHelper.generateToken(TokenType.ACCESS,claims, Instant.ofEpochMilli(authRecord.accessExperation()));
        String refreshCookie = tokenGeneratorServiceHelper.generateToken(TokenType.REFRESH,claims,Instant.ofEpochMilli(authRecord.accessExperation()));
        HttpHeaders headers = new HttpHeaders();

        headers.add(HttpHeaders.SET_COOKIE,accessCookie);
        headers.add(HttpHeaders.SET_COOKIE,refreshCookie);
        return headers;
    }

    private Map<String,Object> setClaims(AuthRecord authRecord){
        return Map.of(ClaimName.USER_ID,authRecord.userId(),
                ClaimName.USER_EMAIL,authRecord.email(),
                ClaimName.USER_ROLE,authRecord.role().name());
    }
}
