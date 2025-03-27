package com.example.dio.service.impl;

import com.example.dio.dto.request.RegistertionRequest;
import com.example.dio.dto.request.UserRequest;
import com.example.dio.dto.response.UserResponse;
import com.example.dio.enums.UserRole;
import com.example.dio.mapper.UserMapper;
import com.example.dio.model.Admin;
import com.example.dio.model.Staff;
import com.example.dio.model.User;
import com.example.dio.repositry.UserRepositry;
import com.example.dio.security.util.UserIdentity;
import com.example.dio.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepositry userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final UserIdentity userIdentity;

    @Override
    public UserResponse registar(RegistertionRequest registertionRequest) {
        User user = this.createUserByRole(registertionRequest.getUserRole());

        userMapper.mapToUserEntity(registertionRequest,user);

        encryptPassword(user);

         userRepository.save(user);

        return userMapper.mapToUserResponse(user);
    }

    public void encryptPassword(User user){
        String encodePassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodePassword);
    }

    @Override
    public UserResponse findUserById() {
        User user = userIdentity.getCurrentUser();
        userRepository.findById(user.getUserid());
        return userMapper.mapToUserResponse(user);
    }

    @Override
    public UserResponse updateUserById(UserRequest userRequest) {
                    User exUser = userIdentity.getCurrentUser();
                    userIdentity.validateOwnerShip(exUser.getUsername());
                    
                    userMapper.mapToUserEntity(userRequest , exUser);
                    userRepository.save(exUser);
                    return userMapper.mapToUserResponse(exUser);
    }
    /**
     * Produce and return child instance of the User Based on the User Role.
     *
     * @param role the role of the user
     * @return User the parent reference containing either of Staff ot Admin Instance
     */
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
