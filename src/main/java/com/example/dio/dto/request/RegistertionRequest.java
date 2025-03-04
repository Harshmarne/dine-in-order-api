package com.example.dio.dto.request;

import com.example.dio.enums.UserRole;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
public class RegistertionRequest {

    @NotEmpty (message = "Username cannot be Empty")
    @NotBlank (message = "Username cannot be blank")
    @Pattern(regexp = "^[a-zA-z0-9_]+$",message = "Username can only contain alphabets,letters and special character")
    private String username;

    @NotEmpty (message = "Email cannot be Empty")
    @NotBlank (message = "Username cannot be blank")
    @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@gmail\\.com$",message = "Email is invalid")
    private String email;

    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,12}$",message = "Atlest one lower and upper letter,one digit and special character,min 8 character and max 12 character")
    private String password;

    @Pattern(regexp = "^[789]\\d{9}$",message = "Phone number is invalid")
    private String phoneNumber;
    private UserRole userRole;
}
