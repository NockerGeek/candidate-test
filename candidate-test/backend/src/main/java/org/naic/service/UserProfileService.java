package org.naic.service;

import org.naic.model.UserProfile;

/**
 * Created by rbarr on 5/16/17.
 */
public interface UserProfileService {

    public UserProfile getUserProfile(long id);

    public UserProfile findByUsername(String username);

    public UserProfile findByEmail(String email);

    public UserProfile authenticate(String username, String password);

    public void updateUserProfile(UserProfile updatedProfile);

}
