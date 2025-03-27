package com.example.dio.security.filters;

import com.example.dio.security.jwt.TokenType;
import jakarta.servlet.http.Cookie;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FilterHelpers {

    public static String extractTokenForCookie(Cookie[] cookies, TokenType tokenType){

        String token = null;
        if(cookies!=null){
            for(Cookie cookie : cookies){
                log.info("Found cookie with name: {} & value: {}", cookie.getName(), cookie.getValue());
                if(cookie.getName().equals(tokenType.type())){
                    token = cookie.getValue();
                    break;
                }
            }
        }
        return token;
    }
}
