package org.naic.service;

import org.naic.security.CurrentUser;

/**
 * Created by rbarr on 5/16/17.
 */
public interface CurrentUserService {

    public boolean canAccessUser(CurrentUser currentUser, Long userId);

}
