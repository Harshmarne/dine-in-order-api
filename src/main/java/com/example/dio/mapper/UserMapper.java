package com.example.dio.mapper;

import com.example.dio.dto.request.RegistertionRequest;
import com.example.dio.dto.request.UserRequest;
import com.example.dio.dto.response.UserResponse;
import com.example.dio.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserMapper {

     void mapToUserEntity(RegistertionRequest registertionRequest,@MappingTarget User user);

     void mapToUserEntity(UserRequest userRequest, @MappingTarget User user);

     UserResponse mapToUserResponse(User user);
}
