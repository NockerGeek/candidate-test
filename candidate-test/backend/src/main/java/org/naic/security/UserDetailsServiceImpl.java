package org.naic.security;

import org.naic.model.UserProfile;
import org.naic.service.UserProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserProfileService userProfileService;

    @Override
    public CurrentUser loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("Looking for " + username + "...");
        UserProfile user = userProfileService.findByUsername(username);
        System.out.println(user.toString());
        return new CurrentUser(user);
    }


}