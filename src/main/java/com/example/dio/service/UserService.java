package com.example.dio.service;

import com.example.dio.model.User;

public interface UserService {

    public User registar(User user);

    User findUserById(long userId);
}
