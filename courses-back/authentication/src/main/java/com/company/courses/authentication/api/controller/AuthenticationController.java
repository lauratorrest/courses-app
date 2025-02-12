package com.company.courses.authentication.api.controller;

import com.company.courses.authentication.api.dto.AuthenticationRequest;
import com.company.courses.authentication.api.dto.RegistrationRequest;
import com.company.courses.authentication.api.dto.ResetPasswordRequest;
import com.company.courses.authentication.api.mapper.AuthenticationModelMapper;
import com.company.courses.authentication.model.AuthenticatedUser;
import com.company.courses.authentication.service.authentication.AuthenticateUserService;
import com.company.courses.authentication.service.authentication.AuthenticationSaveService;
import com.company.courses.authentication.service.authentication.AuthenticationUpdateService;
import com.company.courses.authentication.shared.utils.AppUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Authentication API", description = "End-points for authentication functions")
@RestController
@RequestMapping(AppUtil.APP_MAIN_PATH)
public class AuthenticationController {

    private final AuthenticationSaveService authenticationSaveService;
    private final AuthenticationUpdateService authenticationUpdateService;
    private final AuthenticateUserService authenticateUserService;
    private final AuthenticationModelMapper authenticationModelMapper;

    public AuthenticationController(AuthenticationSaveService authenticationSaveService,
                                    AuthenticationUpdateService authenticationUpdateService,
                                    AuthenticateUserService authenticateUserService,
                                    AuthenticationModelMapper authenticationModelMapper) {
        this.authenticationSaveService = authenticationSaveService;
        this.authenticationUpdateService = authenticationUpdateService;
        this.authenticateUserService = authenticateUserService;
        this.authenticationModelMapper = authenticationModelMapper;
    }

    @Operation(summary = "Save authentication data")
    @PostMapping(AppUtil.SIGN_UP_PATH)
    public ResponseEntity<AuthenticatedUser> saveAuthData(@Valid @RequestBody RegistrationRequest registrationRequest) {
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

    @Operation(summary = "Validate token")
    @PostMapping(AppUtil.VALIDATE_TOKEN + "/{email}/{token}")
    public Boolean validateToken(@PathVariable String email, @PathVariable String token) {
        return this.authenticateUserService.validateToken(token, email);
    }
}
