package com.example.dio.security.config;

import com.example.dio.config.AppEnv;
import com.example.dio.security.filters.AuthFilter;
import com.example.dio.security.filters.RefreshAuthFilter;
import com.example.dio.security.jwt.JwtService;
import com.example.dio.security.jwt.TokenBlackListService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@AllArgsConstructor
public class SecurityConfig {

    private final AppEnv appEnv;
    private final JwtService jwtService;
    private final TokenBlackListService tokenBlackListService;

    private String[] getPublicEndpoints(){
        String[] endpoints = new String[appEnv.getSecurity().getPublicEndpoints().size()];
        for(int i =0 ;i<appEnv.getSecurity().getPublicEndpoints().size();i++){
            endpoints[i] = appEnv.getBaseUrl() + appEnv.getSecurity().getPublicEndpoints().get(i);
        }
        return endpoints;
    }

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    @Order(2)
    SecurityFilterChain securityFilterChain(HttpSecurity security) throws Exception {
        String baseUrl = appEnv.getBaseUrl();
        return security.csrf(csrf -> csrf.disable())
                .securityMatchers(match -> match.requestMatchers(baseUrl + "/**")) //Basically used to configure filter chain to accept request
                .authorizeHttpRequests(authorize ->
                        authorize.requestMatchers( getPublicEndpoints())
                                .permitAll()
                        .anyRequest()
                        .authenticated())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(new AuthFilter(jwtService,tokenBlackListService), UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    @Order(1)
    SecurityFilterChain refreshFilterChain(HttpSecurity security) throws Exception{
        String baseUrl = appEnv.getBaseUrl();
        return security.csrf(csrf -> csrf.disable())
                .securityMatchers(match -> match.requestMatchers(appEnv.getBaseUrl()+"/refresh-login")) //Basically used to configure filter chain to accept request
                .authorizeHttpRequests(authorize -> authorize.anyRequest()
                                        .authenticated())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(new RefreshAuthFilter(jwtService,tokenBlackListService), UsernamePasswordAuthenticationFilter.class)
                .build();
    }
}