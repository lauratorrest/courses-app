package com.company.courses.users.api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class UserUpdateRequest {

    @NotBlank
    @Schema(description = "User ID (only for update)")
    private String id;
    @Schema(description = "User name")
    @NotBlank(message = "User name cannot be blank")
    @Size(min = 5, max = 100, message = "User name must be between {min} and {max} characters")
    @Pattern(regexp = "^[A-Za-zÀ-ÿ\\s]+$", message = "User name can only contain letters and spaces")
    private String name;
    @Schema(description = "User Web Page profile url")
    private String webPageUrl;
    @Schema(description = "User LinkedIn profile url")
    private String linkedInUrl;
    @Schema(description = "User Youtube Channel url")
    private String youtubeChannelUrl;
    @Schema(description = "User Facebook profile url")
    private String facebookUrl;
    @Schema(description = "User Instagram profile url")
    private String instagramUrl;
    @Schema(description = "User profile picture url")
    private String profilePictureUrl;
    @Schema(description = "User profile picture url")
    @Size(max = 100,
            message = "User profession must be {max} characters or less")
    @Pattern(regexp = "^[A-Za-zÀ-ÿ\\s]+$", message = "User profession can only contain letters and spaces")
    private String profession;
    @Size(min = 5, max = 100,
            message = "User description must be between {min} and {max} characters")
    private String aboutMe;
}
