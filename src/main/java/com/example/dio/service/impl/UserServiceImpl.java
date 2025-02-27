package com.example.dio.service.impl;

import com.example.dio.enums.UserRole;
import com.example.dio.model.Admin;
import com.example.dio.model.Staff;
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

    private final UserRepositry userRepository;

    @Override
    public User registar(User user) {
        User user2 = this.createUserByRole(user.getUserRole());

        this.mapToNewUser(user, user2);
        return userRepository.save(user2);
    }

    private User createUserByRole(UserRole role) {
        User user;
        switch (role) {
            case ADMIN -> user = new Admin();
            case STAFF -> user = new Staff();
            default -> throw new RuntimeException("Failed to register user, invalid user type");
        }
        return user;
    }

    private void mapToNewUser(User user, User user2) {
        user2.setUsername(user.getUsername());
        user2.setEmail(user.getEmail());
        user2.setUserRole(user.getUserRole());
        user2.setPhoneNumber(user.getPhoneNumber());
        user2.setPassword(user.getPassword());
    }
}
