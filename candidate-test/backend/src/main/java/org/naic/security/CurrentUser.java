package org.naic.security;

import org.naic.model.UserProfile;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * Created by rbarr on 5/16/17.
 */
public class CurrentUser extends org.springframework.security.core.userdetails.User {

    private User user;
    private UserProfile userProfile;

    public CurrentUser(UserProfile userProfile) {
        super(userProfile.getUsername(), userProfile.getPasswordHash(), AuthorityUtils.createAuthorityList("USER"));
        this.userProfile = userProfile;
    }

    public User getUser() {
        return user;
    }

    public Long getId() {
        return userProfile.getId();
    }

    public Role getRole() {
        return Role.USER;
    }


}
