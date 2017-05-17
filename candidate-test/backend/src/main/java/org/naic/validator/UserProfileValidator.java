package org.naic.validator;

import org.naic.model.UserProfile;
import org.naic.service.UserProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * Created by Robert on 5/16/2017.
 */
@Component
public class UserProfileValidator implements Validator {

    @Autowired
    @Qualifier("basicValidator")
    private Validator basicValidator;

    @Autowired
    private UserProfileService userProfileService;

    @Override
    public boolean supports(Class<?> clazz) {
        return UserProfile.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        basicValidator.validate(target, errors);
        // eventually stop if any errors
        //  if (errors.hasErrors()) { return; }
        UserProfile userProfile = (UserProfile) target;
        if (userProfileService.findByEmail(userProfile.getEmail()) != null) {
            UserProfile dupeProfile = userProfileService.findByEmail(userProfile.getEmail());
            if (dupeProfile.getId() != userProfile.getId()) {
                errors.reject("userProfile.email.notUnique", null, "Email address is already in use.");
            }
        }
    }
}
