package com.company.courses.users.api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class UserCreateRequest {

    @Schema(description = "User name")
    @NotBlank(message = "User name cannot be blank")
    @Size(min = 5, max = 100, message = "User name must be between {min} and {max} characters")
    @Pattern(regexp = "^[A-Za-zÀ-ÿ\\s]+$", message = "User name can only contain letters and spaces")
    private String name;
}
