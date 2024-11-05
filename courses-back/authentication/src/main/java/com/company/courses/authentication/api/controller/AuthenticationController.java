package com.company.courses.authentication.api.controller;

import com.company.courses.authentication.api.dto.AuthenticationRequest;
import com.company.courses.authentication.api.dto.ResetPasswordRequest;
import com.company.courses.authentication.api.mapper.AuthenticationModelMapper;
import com.company.courses.authentication.model.AuthenticatedUser;
import com.company.courses.authentication.service.AuthenticateUserService;
import com.company.courses.authentication.service.AuthenticationSaveService;
import com.company.courses.authentication.service.AuthenticationUpdateService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RequiredArgsConstructor
@Tag(name = "Authentication API", description = "End-points for authentication functions")
@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    private final AuthenticationSaveService authenticationSaveService;
    private final AuthenticationUpdateService authenticationUpdateService;
    private final AuthenticateUserService authenticateUserService;
    private final AuthenticationModelMapper authenticationModelMapper;

    @Operation(summary = "Save authentication data")
    @PostMapping("/save-auth-data")
    public void saveAuthData(@Valid @RequestBody AuthenticationRequest authenticationRequest) {
        this.authenticationSaveService.saveAuthData(this.authenticationModelMapper.toModel(authenticationRequest));
    }

    @Operation(summary = "Authenticate user")
    @PostMapping("/authenticate-user")
    public ResponseEntity<AuthenticatedUser> authenticateUser(
            @Valid @RequestBody AuthenticationRequest authenticationRequest) {
        return ResponseEntity.ok(authenticateUserService.authenticateUser(
                this.authenticationModelMapper.toModel(authenticationRequest)));
    }

    @Operation(summary = "Reset account password")
    @PutMapping("/reset-password")
    public void resetPassword(@Valid @RequestBody ResetPasswordRequest resetPasswordRequest) {
        this.authenticationUpdateService.updateAccountPassword(
                resetPasswordRequest.getEmail(),
                resetPasswordRequest.getCurrentPassword(),
                resetPasswordRequest.getNewPassword1(),
                resetPasswordRequest.getNewPassword2()
        );
    }
}
