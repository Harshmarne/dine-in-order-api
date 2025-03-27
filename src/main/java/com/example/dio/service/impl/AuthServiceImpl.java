package com.example.dio.service.impl;

import com.example.dio.config.AppEnv;
import com.example.dio.dto.request.AuthRecord;
import com.example.dio.dto.request.LoginRequest;
import com.example.dio.model.User;
import com.example.dio.repositry.UserRepositry;
import com.example.dio.security.jwt.ClaimName;
import com.example.dio.security.jwt.JwtService;
import com.example.dio.security.jwt.TokenBlackListService;
import com.example.dio.security.util.CookieManager;
import com.example.dio.service.AuthService;
import io.jsonwebtoken.Claims;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
@AllArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final AuthenticationManager authenticationManager;
    private final UserRepositry userRepositry;
    private final JwtService jwtService;
    private final TokenBlackListService tokenBlackListService;
    private final CookieManager cookieManager;
    private final AppEnv appEnv;

    @Override
    public AuthRecord login(LoginRequest loginRequest) {
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(loginRequest.email(),loginRequest.password());
        Authentication authentication =authenticationManager.authenticate(token);
        if(authentication.isAuthenticated()){
            User user = userRepositry.findByEmail(loginRequest.email())
                    .orElseThrow(() -> new UsernameNotFoundException("User Not Found"));

            return generetAuthRecord(user);
        }
        else {
            throw new UsernameNotFoundException("Failed");
        }
    }

    @Override
    public AuthRecord refreshAccessToken(String refreshToken) {
        Claims claims = jwtService.parseToken(refreshToken);

        String email = claims.get(ClaimName.USER_EMAIL, String.class);
        User user = userRepositry.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User  Not Found"));

        long refreshExpiration = claims.getExpiration().toInstant().toEpochMilli();


        // Generate new access token
        long newAccessExpiration = Instant.now().plusSeconds(3600L).toEpochMilli(); // 1 hour

       AuthRecord authRecord= new AuthRecord(
                user.getUserid(),
                user.getUsername(),
                user.getEmail(),
                user.getUserRole(),
                newAccessExpiration,
                refreshExpiration

        );
        return authRecord;
    }

    @Override
    public HttpHeaders logout(String refreshToken, String accessToken) {
        tokenBlackListService.blackListToken(refreshToken);
        tokenBlackListService.blackListToken(accessToken);

        String refreshCookie = cookieManager.generateCookie("rt","",0);
        String accessCookie = cookieManager.generateCookie("at","",0);

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.SET_COOKIE,refreshCookie);
        headers.add(HttpHeaders.SET_COOKIE,accessCookie);

        return headers;
    }

    private AuthRecord generetAuthRecord(User user) {
        Instant now = Instant.now();

        long accessExprition = now.plusSeconds(appEnv.getSecurity().getTokenValidity().getAccessValidity()).toEpochMilli(); // 1hours
        long refreshExprition = now.plusSeconds(appEnv.getSecurity().getTokenValidity().getRefreshValidity()).toEpochMilli(); // 2 month

        AuthRecord authRecord = new AuthRecord(
                user.getUserid(),
                user.getUsername(),
                user.getEmail(),
                user.getUserRole(),
                accessExprition,
                refreshExprition);

        return authRecord;
    }
}
