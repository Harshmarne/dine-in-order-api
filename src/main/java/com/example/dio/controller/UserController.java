package com.example.dio.controller;

import com.example.dio.model.User;
import com.example.dio.service.UserService;
import com.example.dio.utility.ResponseStructure;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/registar")
    public ResponseEntity<ResponseStructure<User>> registarUser(@RequestBody User user){
        user = userService.registar(user);

        ResponseStructure<User> structure = new ResponseStructure<>();
        structure.setData(user);
        structure.setStatus(HttpStatus.CREATED.value());
        structure.setMessage("User Object Created Successfully!!");

        return new ResponseEntity<ResponseStructure<User>>(structure , HttpStatus.CREATED);
    }
}
