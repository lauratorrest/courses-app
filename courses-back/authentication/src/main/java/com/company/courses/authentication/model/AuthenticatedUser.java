package com.company.courses.authentication.model;

import com.company.courses.authentication.model.enums.UserRoleEnum;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Setter
@Getter
@Builder
public class AuthenticatedUser {

    private String token;
    private String userEmail;
    private UserRoleEnum userRole;
    private String accountId;
    private String accountStatus;
    private LocalDateTime accountCreatedDate;
    private LocalDateTime accountUpdatedDate;
    private String userId;
    private String userName;
    private String password;
    private String userProfilePicUrl;
    private String userProfession;
    private LocalDateTime userUpdatedDate;
    private List<String> userMadeCoursesIds;
    private List<String> userBoughtCoursesIds;
    private List<String> userCartCoursesIds;
}
