package com.example.dio.controller;

import com.example.dio.dto.request.RegistertionRequest;
import com.example.dio.dto.request.UserRequest;
import com.example.dio.dto.response.UserResponse;
import com.example.dio.model.User;
import com.example.dio.service.UserService;
import com.example.dio.service.impl.UserServiceImpl;
import com.example.dio.utility.FieldErrorResponse;
import com.example.dio.utility.ResponseBuilder;
import com.example.dio.utility.ResponseStructure;
import com.example.dio.utility.SimpleErrorResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import lombok.*;

@RestController
@AllArgsConstructor
@Tag(name = "User Controller",description = "Collection of APIs Endpoints Dealing with user Data")
public class UserController {

    private final UserService userService;

    @PostMapping("/register")
    @Operation(description = """
            The API Endpoints to Register the New User in Database
            """,
        responses = {
            @ApiResponse(responseCode = "201",description = "User Created"),
                @ApiResponse(responseCode = "404",description = "Invalid Input",content = {
                        @Content(schema = @Schema(implementation = FieldErrorResponse.class))
                })
        })
    public ResponseEntity<ResponseStructure<UserResponse>> registerUser(@Valid @RequestBody RegistertionRequest registertionRequest) {
        UserResponse userResponse = userService.registar(registertionRequest);
        return ResponseBuilder.success(HttpStatus.CREATED, "User Created", userResponse);
    }

    @GetMapping("/users/{userId}")
    @Operation(description = """
            The API Endpoints to Find The User By Id
            """,
            responses = {
                    @ApiResponse(responseCode = "200",description = "User Found"),
                    @ApiResponse(responseCode = "404",description = "Invalid Input",content = {
                            @Content(schema = @Schema(implementation = SimpleErrorResponse.class))
                    })
            })
    public ResponseEntity<ResponseStructure<UserResponse>> findUserById(@PathVariable Long userId) {
        UserResponse userResponse = userService.findUserById(userId);
        return ResponseBuilder.success(HttpStatus.OK, "User Found", userResponse);
    }

    @PutMapping("/users/{userId}")
    @Operation(description = """
            The API Endpoints to Register the New User in Database
            """,
            responses = {
                    @ApiResponse(responseCode = "200",description = "User Founded and Updated"),
                    @ApiResponse(responseCode = "404",description = "Invalid Input",content = {
                            @Content(schema = @Schema(implementation = SimpleErrorResponse.class))
                    })
            })
    public ResponseEntity<ResponseStructure<UserResponse>> updateById(@RequestBody UserRequest userRequest,@PathVariable long userId){

        UserResponse userResponse = userService.updateUserById(userRequest,userId);
        return ResponseBuilder.success(HttpStatus.OK, "User Found", userResponse);

    }

}
