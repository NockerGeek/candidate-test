package org.naic.service;

import org.naic.model.UserProfile;
import org.springframework.stereotype.Service;

/**
 * Created by rbarr on 5/16/17.
 */
@Service
public class UserProfileService {

    private UserProfile userProfile;

    public UserProfile getUserProfile() {
        if (userProfile == null) {
            populateUserProfile();
        }
        return userProfile;
    }

    public void setUserProfile(UserProfile userProfile) {
        this.userProfile = userProfile;
    }

    private void populateUserProfile() {
        userProfile = new UserProfile();
        userProfile.setFirstName("Test");
        userProfile.setLastName("Testerson");
    }
}
