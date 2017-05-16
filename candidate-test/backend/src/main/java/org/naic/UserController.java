package org.naic;

/**
 * Created by Robert on 5/15/2017.
 */

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;

@RestController
@RequestMapping("/users")
public class UserController {

    private UserProfile userProfile = new UserProfile();
    private static final Logger LOG = LoggerFactory.getLogger(UserController.class);

    @PostConstruct
    public void init() {
        populateUserProfile();
    }

    @RequestMapping(method = RequestMethod.GET)
    public UserProfile getUserProfile() {
        return userProfile;
    }

    @RequestMapping(method = RequestMethod.POST)
    public UserProfile updateUserProfile(@RequestBody UserProfile updatedProfile) {
        userProfile = updatedProfile;
        LOG.info(userProfile.toString());

        return userProfile;
    }

    private void populateUserProfile() {
        userProfile.setFirstName("Test");
        userProfile.setLastName("Testerson");
    }

}
