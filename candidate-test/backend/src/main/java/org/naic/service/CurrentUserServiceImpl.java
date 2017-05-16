package org.naic.service;

import org.naic.security.CurrentUser;
import org.naic.security.Role;
import org.springframework.stereotype.Service;

/**
 * Created by rbarr on 5/16/17.
 */
@Service
public class CurrentUserServiceImpl implements CurrentUserService {

    @Override
    public boolean canAccessUser(CurrentUser currentUser, Long userId) {
        return currentUser != null
                && (currentUser.getRole() == Role.ADMIN || currentUser.getId().equals(userId));
    }

}
