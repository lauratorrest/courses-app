package com.company.courses.authentication.api.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AuthenticationRequest {

    @Schema(description = "User email")
    @NotBlank(message = "User email cannot be blank")
    @Size(max = 100, message = "User email must be {max} characters or less")
    @Pattern(regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$", message = "Invalid email format")
    private String email;

    @Schema(description = "User password")
    @NotBlank(message = "User password cannot be blank")
    @Size(max = 100, message = "User password must be {max} characters or less")
    @Pattern(regexp = "^(?=.*[A-Za-z0-9@#$%^&+=*]).{8,100}$",
            message = "Password must contain letters, numbers and special characters, and be at least 8 characters long")
    private String password;
}
