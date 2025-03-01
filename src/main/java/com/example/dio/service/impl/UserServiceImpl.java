package com.example.dio.service.impl;

import com.example.dio.dto.request.RegistertionRequest;
import com.example.dio.dto.request.UserRequest;
import com.example.dio.dto.response.UserResponse;
import com.example.dio.enums.UserRole;
import com.example.dio.exception.UserNotFoundByIdException;
import com.example.dio.mapper.UserMapper;
import com.example.dio.model.Admin;
import com.example.dio.model.Staff;
import com.example.dio.model.User;
import com.example.dio.repositry.UserRepositry;
import com.example.dio.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepositry userRepository;
    private final UserMapper userMapper;

    @Override
    public UserResponse registar(RegistertionRequest registertionRequest) {
        User user = this.createUserByRole(registertionRequest.getUserRole());

        userMapper.mapToUserEntity(registertionRequest,user);

         userRepository.save(user);

        return userMapper.mapToUserResponse(user);
    }

    @Override
    public UserResponse findUserById(long userId) {
        return  userRepository.findById(userId)
                .map(userMapper::mapToUserResponse)
                .orElseThrow(() -> new UserNotFoundByIdException("Failed to find user, user not found by Id"));
    }

    @Override
    public UserResponse updateUserById(UserRequest userRequest, long userId) {
//        User exuser = userRepository.findById(userId)
//                .orElseThrow(()-> new UserNotFoundByIdException("Failed to find user, user not found by Id"));
//
            //may have to delele this
//        userMapper.mapToUserEntity(userRequest , exuser);
//        User user = userRepository.save(exuser);
//        return userMapper.mapToUserResponse(user);

        //Lamda Expresion
        return userRepository.findById(userId)
                .map(exUser -> {
                    userMapper.mapToUserEntity(userRequest , exUser);
                    userRepository.save(exUser);
                    return userMapper.mapToUserResponse(exUser);
                })
                    .orElseThrow(()-> new UserNotFoundByIdException("Failed to find user, user not found by Id"));

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
}
