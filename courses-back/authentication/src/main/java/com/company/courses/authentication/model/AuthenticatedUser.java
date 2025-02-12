package com.company.courses.authentication.model;

import com.company.courses.authentication.model.enums.AccountStatusEnum;
import com.company.courses.authentication.model.enums.UserRoleEnum;

import java.time.LocalDateTime;
import java.util.List;

public class AuthenticatedUser {

    private String token;
    private String userEmail;
    private UserRoleEnum userRole;
    private String accountId;
    private final AccountStatusEnum accountStatus;
    private final LocalDateTime accountCreatedDate;
    private final LocalDateTime accountUpdatedDate;
    private final String userId;
    private final String userName;
    private String password;
    private String userProfilePicUrl;
    private String userProfession;
    private List<String> userMadeCoursesIds;
    private List<String> userBoughtCoursesIds;
    private List<String> userCartCoursesIds;

    public AuthenticatedUser(String token,
                             String userEmail,
                             UserRoleEnum userRole,
                             String accountId,
                             AccountStatusEnum accountStatus,
                             LocalDateTime accountCreatedDate,
                             LocalDateTime accountUpdatedDate,
                             String userId,
                             String userName) {
        this.token = token;
        this.userEmail = userEmail;
        this.userRole = userRole;
        this.accountId = accountId;
        this.accountStatus = accountStatus;
        this.accountCreatedDate = accountCreatedDate;
        this.accountUpdatedDate = accountUpdatedDate;
        this.userId = userId;
        this.userName = userName;
    }

    public AuthenticatedUser(Account account,
                             UserResponse user) {
        this.userId = user.getId();
        this.userName = user.getName();
        this.accountCreatedDate = account.getCreatedDate();
        this.accountUpdatedDate = account.getUpdatedDate();
        this.accountStatus = account.getStatus();
        this.userProfilePicUrl = user.getProfilePictureUrl();
        this.userProfession = user.getProfession();
        this.userMadeCoursesIds = user.getMadeCoursesIds();
        this.userBoughtCoursesIds = user.getBoughtCoursesIds();
        this.userCartCoursesIds = user.getCartCoursesIds();
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public UserRoleEnum getUserRole() {
        return userRole;
    }

    public void setUserRole(UserRoleEnum userRole) {
        this.userRole = userRole;
    }

    public String getAccountId() {
        return accountId;
    }

    public AccountStatusEnum getAccountStatus() {
        return accountStatus;
    }

    public LocalDateTime getAccountCreatedDate() {
        return accountCreatedDate;
    }

    public LocalDateTime getAccountUpdatedDate() {
        return accountUpdatedDate;
    }

    public String getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public String getUserProfilePicUrl() {
        return userProfilePicUrl;
    }

    public String getUserProfession() {
        return userProfession;
    }

    public List<String> getUserMadeCoursesIds() {
        return userMadeCoursesIds;
    }

    public List<String> getUserBoughtCoursesIds() {
        return userBoughtCoursesIds;
    }

    public List<String> getUserCartCoursesIds() {
        return userCartCoursesIds;
    }
}
