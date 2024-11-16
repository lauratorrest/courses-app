package com.company.courses.authentication.api.controller;

import com.company.courses.authentication.api.dto.AuthenticationRequest;
import com.company.courses.authentication.api.dto.RegistrationRequest;
import com.company.courses.authentication.api.dto.ResetPasswordRequest;
import com.company.courses.authentication.api.mapper.AuthenticationModelMapper;
import com.company.courses.authentication.model.AuthenticatedUser;
import com.company.courses.authentication.model.AuthenticationData;
import com.company.courses.authentication.service.AuthenticateUserService;
import com.company.courses.authentication.service.AuthenticationSaveService;
import com.company.courses.authentication.service.AuthenticationUpdateService;
import com.company.courses.authentication.shared.utils.AppUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@Tag(name = "Authentication API", description = "End-points for authentication functions")
@RestController
@RequestMapping(AppUtil.APP_MAIN_PATH)
public class AuthenticationController {

    private final AuthenticationSaveService authenticationSaveService;
    private final AuthenticationUpdateService authenticationUpdateService;
    private final AuthenticateUserService authenticateUserService;
    private final AuthenticationModelMapper authenticationModelMapper;

    @Operation(summary = "Save authentication data")
    @PostMapping(AppUtil.SIGN_UP_PATH)
    public ResponseEntity<AuthenticationData> saveAuthData(@Valid @RequestBody RegistrationRequest registrationRequest) {
        return ResponseEntity.ok(this.authenticationSaveService.saveAuthData(
                registrationRequest.getEmail(), registrationRequest.getPassword(), registrationRequest.getUserName()
        ));
    }

    @Operation(summary = "Authenticate user")
    @PostMapping(AppUtil.LOGIN_PATH)
    public ResponseEntity<AuthenticatedUser> authenticateUser(
            @Valid @RequestBody AuthenticationRequest authenticationRequest) {
        return ResponseEntity.ok(authenticateUserService.authenticateUser(
                this.authenticationModelMapper.toModel(authenticationRequest)));
    }

    @Operation(summary = "Reset account password")
    @PutMapping(AppUtil.FORGOT_PASSWORD_PATH)
    public void resetPassword(@Valid @RequestBody ResetPasswordRequest resetPasswordRequest) {
        this.authenticationUpdateService.updateAccountPassword(
                resetPasswordRequest.getEmail(),
                resetPasswordRequest.getCurrentPassword(),
                resetPasswordRequest.getNewPassword1(),
                resetPasswordRequest.getNewPassword2()
        );
    }
}
