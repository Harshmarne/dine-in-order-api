package com.example.dio.service.impl;

import com.example.dio.dto.request.AuthRecord;
import com.example.dio.dto.request.LoginRequest;
import com.example.dio.model.User;
import com.example.dio.repositry.UserRepositry;
import com.example.dio.service.AuthService;
import lombok.AllArgsConstructor;
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

    private static AuthRecord generetAuthRecord(User user) {
        Instant now = Instant.now();

        long accseExprition = now.plusSeconds(3600L).toEpochMilli(); // 1hours
        long referseExprition = now.plusSeconds((2*30*24*60*60L)).toEpochMilli(); // 2 month

        AuthRecord authRecord = new AuthRecord(
                user.getUserid(),
                user.getUsername(),
                user.getEmail(),
                user.getUserRole(),
                accseExprition,
                referseExprition);

        return authRecord;
    }
}
