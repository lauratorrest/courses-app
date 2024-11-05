package com.company.courses.authentication.api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class ResetPasswordRequest {

    @NotBlank
    @Schema(description = "User email")
    private String email;

    @NotBlank
    @Schema(description = "User current password")
    private String currentPassword;

    @Schema(description = "User new password 1")
    @NotBlank(message = "User password cannot be blank")
    @Size(min = 8, max = 100, message = "User password must be {max} characters or less")
    @Pattern(regexp = "^(?=.*[A-Za-z0-9@#$%^&+=*]).{8,100}$",
            message = "Password must contain letters, numbers and special characters, and be at least 8 characters long")
    private String newPassword1;

    @Schema(description = "User new password 2")
    @NotBlank(message = "User password cannot be blank")
    @Size(min = 8, max = 100, message = "User password must be {max} characters or less")
    @Pattern(regexp = "^(?=.*[A-Za-z0-9@#$%^&+=*]).{8,100}$",
            message = "Password must contain letters, numbers and special characters, and be at least 8 characters long")
    private String newPassword2;
}
