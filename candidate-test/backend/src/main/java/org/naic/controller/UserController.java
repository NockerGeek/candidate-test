package org.naic.controller;

/**
 * Created by Robert on 5/15/2017.
 */

import org.naic.model.UserProfile;
import org.naic.service.UserProfileService;
import org.naic.validator.UserProfileValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/users")

public class UserController {

    @Autowired
    private UserProfileService profileService;

    @Autowired
    private UserProfileValidator validator;

    @InitBinder
    protected void initBinder(final WebDataBinder binder) {
        binder.addValidators(validator);
    }

    private static final Logger LOG = LoggerFactory.getLogger(UserController.class);

    @RequestMapping(value = "/authenticate", method = RequestMethod.GET)
    public UserProfile authenticate(@RequestParam(value = "username", required = true) String username,
                                    @RequestParam(value = "password", required = true) String password) {
        return profileService.authenticate(username, password);
    }

    @PreAuthorize("@currentUserServiceImpl.canAccessUser(principal, #id)")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public UserProfile getUserProfile(@PathVariable long id) {
        return profileService.getUserProfile(id);
    }

    @PreAuthorize("@currentUserServiceImpl.canAccessUser(principal, #id)")
    @RequestMapping(value = "/{id}", method = RequestMethod.POST)
    public UserProfile updateUserProfile(@PathVariable long id, @RequestBody @Valid UserProfile updatedProfile) {
        profileService.updateUserProfile(updatedProfile);
        UserProfile userProfile = profileService.getUserProfile(updatedProfile.getId());
        LOG.info(userProfile.toString());
        return userProfile;
    }

}
