package org.naic.data;

import org.naic.model.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by rbarr on 5/16/17.
 */
@Repository("userProfileRepository")
public interface UserProfileRepository extends JpaRepository<UserProfile, Long> {
    UserProfile findByUsername(String username);

    UserProfile findByEmail(String email);
}
