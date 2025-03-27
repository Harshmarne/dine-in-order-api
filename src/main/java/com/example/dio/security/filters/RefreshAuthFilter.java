package com.example.dio.security.filters;

import com.example.dio.security.jwt.ClaimName;
import com.example.dio.security.jwt.JwtService;
import com.example.dio.security.jwt.TokenBlackListService;
import com.example.dio.security.jwt.TokenType;
import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@Slf4j
@AllArgsConstructor
public class RefreshAuthFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final TokenBlackListService tokenBlackListService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        Cookie[] cookies = request.getCookies();
        String token = FilterHelpers.extractTokenForCookie(cookies, TokenType.REFRESH);
        if(token!=null || tokenBlackListService.isBlackListed(token)){
            Claims claims = jwtService.parseToken(token);
            String email = claims.get(ClaimName.USER_EMAIL, String.class);
            String role = claims.get(ClaimName.USER_ROLE,String.class);

            if((email != null && !email.isBlank()) && (role != null && !role.isBlank())){
                if(SecurityContextHolder.getContext().getAuthentication() == null){
                    UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                            email,
                            null,
                            List.of(new SimpleGrantedAuthority(role)));

                    authenticationToken.setDetails(request);
                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                }
            }
        }
        filterChain.doFilter(request,response);
    }
}
