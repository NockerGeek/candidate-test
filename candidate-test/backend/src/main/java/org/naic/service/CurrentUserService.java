package org.naic.service;

import org.naic.security.CurrentUser;
import org.naic.security.Role;

/**
 * Created by rbarr on 5/16/17.
 */
public interface CurrentUserService {

    public boolean canAccessUser(CurrentUser currentUser, Long userId);

}
