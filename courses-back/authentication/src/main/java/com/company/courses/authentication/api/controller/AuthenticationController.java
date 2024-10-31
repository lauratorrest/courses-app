package com.company.courses.authentication.api.controller;

import com.company.courses.authentication.service.AuthenticateUserService;
import com.company.courses.authentication.service.AuthenticationSaveService;
import com.company.courses.authentication.service.AuthenticationUpdateService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@Validated
@RequiredArgsConstructor
@Tag(name = "Authentication API", description = "End-points for authentication functions")
@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {

    private final AuthenticationSaveService authenticationSaveService;
    private final AuthenticationUpdateService authenticationUpdateService;
    private final AuthenticateUserService authenticateUserService;

    @Operation(summary = "Save authentication data")
    @PostMapping("/save-auth-data")
    public void saveAuthData(
            @RequestParam
            @Schema(description = "User password")
            @NotBlank(message = "User password cannot be blank")
            @Size(max = 100, message = "User password must be {max} characters or less")
            @Pattern(regexp = "^(?=.*[A-Za-z0-9@#$%^&+=*]).{8,100}$",
                    message = "Password must contain letters, numbers and special characters, and be at least 8 characters long")
            String password
    ) {
        this.authenticationSaveService.saveAuthData(password);
    }

    @Operation(summary = "Authenticate user")
    @PostMapping("/authenticate-user")
    public ResponseEntity<HashMap<String, String>> validatePassword(
            @RequestParam String authId,
            @RequestParam String givenPassword
    ) {
        HashMap<String, String> response = new HashMap<>();
        response.put("token", this.authenticateUserService.authenticateUser(authId, givenPassword));
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Reset account password")
    @PutMapping("/reset-password")
    public void resetPassword(
            @RequestParam String authId,
            @RequestParam String currentPassword,
            @Schema(description = "User password")
            @NotBlank(message = "User password cannot be blank")
            @Size(max = 100, message = "User password must be {max} characters or less")
            @Pattern(regexp = "^(?=.*[A-Za-z0-9@#$%^&+=*]).{8,100}$",
                    message = "Password must contain letters, numbers and special characters, and be at least 8 characters long")
            @RequestParam String newPassword1,
            @RequestParam String newPassword2
    ) {
        this.authenticationUpdateService.updateAccountPassword(authId, currentPassword, newPassword1, newPassword2);
    }
}
