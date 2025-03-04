package com.example.dio.dto.response;

import com.example.dio.enums.UserRole;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Builder
@Getter
@Setter
public class UserResponse {
    private long userid;
    private String username;
    private UserRole userRole;
    private LocalDate createAt;
    private LocalDate lastModifiedAt;


}
