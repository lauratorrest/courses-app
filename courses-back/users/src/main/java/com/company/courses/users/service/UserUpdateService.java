package com.company.courses.users.service;

import com.company.courses.users.model.User;
import com.company.courses.users.repository.UserRepository;
import com.company.courses.users.shared.utils.StringFixProcess;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserUpdateService {

    private final UserRepository userRepository;
    private final UserGetService userGetService;
    private final StringFixProcess stringFixProcess = new StringFixProcess();

    public User updateUserInformation(User user) {
        User currentUser = this.userGetService.getUserInformation(user.getId());
        stringFixProcess.fixUserName(user.getName());
        stringFixProcess.fixParagraph(user.getProfession());
        stringFixProcess.fixParagraph(user.getAboutMe());
        this.setUserInfo(user, currentUser);
        return this.userRepository.save(currentUser);
    }

    private void setUserInfo(User newUser, User currentUser) {
        currentUser.setName(newUser.getName());
        currentUser.setWebPageUrl(newUser.getWebPageUrl());
        currentUser.setLinkedInUrl(newUser.getLinkedInUrl());
        currentUser.setYoutubeChannelUrl(newUser.getYoutubeChannelUrl());
        currentUser.setFacebookUrl(newUser.getFacebookUrl());
        currentUser.setInstagramUrl(newUser.getInstagramUrl());
        currentUser.setProfilePictureUrl(newUser.getProfilePictureUrl());
        currentUser.setProfession(newUser.getProfession());
        currentUser.setAboutMe(newUser.getAboutMe());
    }
}
