package com.example.dio.service.helper;

import com.example.dio.config.AppEnv;
import com.example.dio.security.jwt.JwtService;
import com.example.dio.security.jwt.TokenPayload;
import com.example.dio.security.jwt.TokenType;
import com.example.dio.security.util.CookieManager;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.Instant;
import java.util.Map;

@Component
@AllArgsConstructor
public class TokenGeneratorServiceHelper {

    private final AppEnv appEnv;
    private final JwtService jwtService;
    private final CookieManager cookieManager;

    public String generateToken(TokenType tokenType, Map<String, Object> claims, Instant shouldExpireAt){
        TokenPayload tokenPayload = generateTokenPlayload(tokenType,claims,shouldExpireAt);
        String token = jwtService.generateToken(tokenPayload);
        long maxAge = Duration.between(Instant.now(),shouldExpireAt).getSeconds();
        return cookieManager.generateCookie(tokenType.type(),token,maxAge);
    }

    private TokenPayload generateTokenPlayload(TokenType tokenType, Map<String, Object> claims, Instant shouldExpireAt){
        Instant issuAt = calculateIssueType(tokenType, shouldExpireAt);

        return new TokenPayload(claims,issuAt,shouldExpireAt);
    }

    private Instant calculateIssueType(TokenType tokenType, Instant shouldExpireAt) {
        Instant issueAt;
        switch (tokenType){
            case ACCESS -> {
                issueAt = shouldExpireAt.minusSeconds(appEnv.getSecurity().getTokenValidity().getAccessValidity());
            }
            case REFRESH -> {
                issueAt = shouldExpireAt.minusSeconds(appEnv.getSecurity().getTokenValidity().getRefreshValidity());
            }
            default -> throw new IllegalArgumentException("Invalid Token Type");
        }
        return issueAt;
    }

}
