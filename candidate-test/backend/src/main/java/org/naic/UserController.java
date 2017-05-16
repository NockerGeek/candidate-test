package org.naic;

/**
 * Created by Robert on 5/15/2017.
 */

import org.naic.model.UserProfile;
import org.naic.service.UserProfileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import javax.validation.Valid;

@RestController
@RequestMapping("/users")
public class UserController {

    private UserProfile userProfile;

    @Autowired
    private UserProfileService profileService;

    private static final Logger LOG = LoggerFactory.getLogger(UserController.class);

    @RequestMapping(method = RequestMethod.GET)
    @Secured("USER")
    public UserProfile getUserProfile() {
        return profileService.getUserProfile();
    }

    @RequestMapping(method = RequestMethod.POST)
    @Secured("USER")
    public UserProfile updateUserProfile(@RequestBody @Valid UserProfile updatedProfile) {
        profileService.setUserProfile(updatedProfile);
        userProfile = profileService.getUserProfile();
        LOG.info(userProfile.toString());
        return userProfile;
    }



}
