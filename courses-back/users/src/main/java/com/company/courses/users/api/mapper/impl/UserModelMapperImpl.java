package com.company.courses.users.api.mapper.impl;

import com.company.courses.users.api.dto.UserUpdateRequest;
import com.company.courses.users.api.mapper.UserModelMapper;
import com.company.courses.users.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserModelMapperImpl implements UserModelMapper {

    @Override
    public User toUserModel(UserUpdateRequest userUpdateRequest) {
        return User.builder()
                .id(userUpdateRequest.getId())
                .name(userUpdateRequest.getName())
                .webPageUrl(userUpdateRequest.getWebPageUrl())
                .linkedInUrl(userUpdateRequest.getLinkedInUrl())
                .youtubeChannelUrl(userUpdateRequest.getYoutubeChannelUrl())
                .facebookUrl(userUpdateRequest.getFacebookUrl())
                .instagramUrl(userUpdateRequest.getInstagramUrl())
                .profilePictureUrl(userUpdateRequest.getProfilePictureUrl())
                .profession(userUpdateRequest.getProfession())
                .aboutMe(userUpdateRequest.getAboutMe())
                .build();
    }
}
