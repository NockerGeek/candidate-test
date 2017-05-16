package org.naic;

/**
 * Created by Robert on 5/15/2017.
 */

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    private UserProfile userProfile = new UserProfile();

    @RequestMapping(method = RequestMethod.GET)
    public UserProfile getUserProfile() {
        populateUserProfile();
        return userProfile;
    }

    @RequestMapping(method = RequestMethod.POST)
    public UserProfile updateUserProfile(@RequestBody UserProfile updatedProfile) {
        userProfile = updatedProfile;
        return userProfile;
    }

    private void populateUserProfile() {
        userProfile.setFirstName("Test");
        userProfile.setLastName("Testerson");
    }

}
