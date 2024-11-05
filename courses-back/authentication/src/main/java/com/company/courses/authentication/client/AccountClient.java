package com.company.courses.authentication.client;

import com.company.courses.authentication.model.AuthenticatedUser;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;

@Service
public class AccountClient {

    public AuthenticatedUser getAccountWithUserDataByAuthId(String authId){
        return AuthenticatedUser
                .builder()
                .userId("my_id")
                .accountCreatedDate(LocalDateTime.now())
                .accountUpdatedDate(LocalDateTime.now())
                .userProfilePicUrl("pic_url")
                .userProfession("profession")
                .userUpdatedDate(LocalDateTime.now())
                .userMadeCoursesIds(new ArrayList<>())
                .userBoughtCoursesIds(new ArrayList<>())
                .userCartCoursesIds(new ArrayList<>())
                .build();
    }
}
