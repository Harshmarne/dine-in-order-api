package com.example.dio.service.impl;

import com.example.dio.model.User;
import com.example.dio.repositry.UserRepositry;
import com.example.dio.service.UserService;
import com.example.dio.utility.ResponseStructure;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepositry userRepositry;

    @Override
    public User registar(User user) {
        return userRepositry.save(user);
    }
}
