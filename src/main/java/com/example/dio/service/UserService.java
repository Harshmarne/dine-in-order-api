package com.example.dio.service;

import com.example.dio.model.User;
import com.example.dio.utility.ResponseStructure;
import org.springframework.http.ResponseEntity;

public interface UserService {

    public User registar(User user);
}
