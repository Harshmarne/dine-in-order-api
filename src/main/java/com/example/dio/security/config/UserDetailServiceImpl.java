package com.example.dio.security.config;

import com.example.dio.repositry.UserRepositry;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserDetailServiceImpl implements UserDetailsService {

    private final UserRepositry userRepositry;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepositry.findByEmail(username)
                .map(this::createAuthUser)
                .orElseThrow(()-> new UsernameNotFoundException("Failed To Authenticate User ,  User Not Found By Email"));

    }

    public UserDetails createAuthUser(com.example.dio.model.User user){
        return User.builder()
                .username(user.getEmail())
                .password(user.getPassword())
                .authorities(user.getUserRole().name())
                .build();
    }
}
