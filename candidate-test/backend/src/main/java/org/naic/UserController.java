package org.naic;

/**
 * Created by Robert on 5/15/2017.
 */

import org.naic.data.UserProfileRepository;
import org.naic.model.UserProfile;
import org.naic.service.UserProfileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import javax.validation.Valid;

@RestController
@RequestMapping("/users")
public class UserController {


    @Autowired
    private UserProfileService profileService;

    private static final Logger LOG = LoggerFactory.getLogger(UserController.class);

    @RequestMapping(value = "/authUser", method = RequestMethod.GET)
    //@Secured("USER")
    public UserProfile getUserProfile(@RequestParam(value="username", required=true) String username,
                                      @RequestParam(value="password", required=true) String password) {
        return profileService.authenticateUserProfile(username, password);
    }

    @PreAuthorize("@currentUserServiceImpl.canAccessUser(principal, #id)")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    //@Secured("USER")
    public UserProfile getUserProfile(@PathVariable long id) {
        return profileService.getUserProfile(id);
    }

    @PreAuthorize("@currentUserServiceImpl.canAccessUser(principal, #id)")
    @RequestMapping(value = "/{id}", method = RequestMethod.POST)
    //@Secured("USER")
    public UserProfile updateUserProfile(@PathVariable long id, @RequestBody @Valid UserProfile updatedProfile) {
        profileService.updateUserProfile(updatedProfile);
        UserProfile userProfile = profileService.getUserProfile(updatedProfile.getId());
        LOG.info(userProfile.toString());
        return userProfile;
    }



}
