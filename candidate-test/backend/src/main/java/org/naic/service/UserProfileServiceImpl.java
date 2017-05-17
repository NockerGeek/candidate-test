package org.naic.service;

import org.naic.data.UserProfileRepository;
import org.naic.model.UserProfile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

/**
 * Created by rbarr on 5/16/17.
 */
@Service
public class UserProfileServiceImpl implements UserProfileService {

    @Autowired
    private UserProfileRepository repository;

    @PostConstruct
    public void init() {
        UserProfile profile1 = new UserProfile("rbarr", "pass111", "Rob", "Barr", "rbarr.1234@gmail.com");
        repository.save(profile1);
        UserProfile profile2 = new UserProfile("bob", "pass222", "Bob", "Barr", "bob.5678@gmail.com");
        repository.save(profile2);
        UserProfile profile3 = new UserProfile("dude", "pass333", "Dude", "Barr", "dude.9012@gmail.com");
        repository.save(profile3);
    }

    public UserProfile getUserProfile(long id) {
        UserProfile userProfile = repository.findOne(id);

        if (userProfile == null) {
            populateUserProfile(userProfile);
        }
        return userProfile;
    }

    public UserProfile findByUsername(String username) {
        return repository.findByUsername(username);
    }

    public UserProfile findByEmail(String email) {
        return repository.findByEmail(email);
    }

    public UserProfile authenticate(String username, String password) {
        // This is ugly, but without a real DB and only 3 records, it'll do
        UserProfile authProfile = repository.findByUsername(username);
        if (authProfile != null) {
            if (authProfile.getPassword().equals(password)) {
                return authProfile;
            }
        }

        return new UserProfile("", "", "", "", "");
    }

    public void updateUserProfile(UserProfile updatedProfile) {
        UserProfile userProfile = repository.findOne(updatedProfile.getId());
        if (userProfile != null) {
            userProfile.setFirstName(updatedProfile.getFirstName());
            userProfile.setLastName(updatedProfile.getLastName());
            userProfile.setEmail(updatedProfile.getEmail());
            userProfile.setAddress1(updatedProfile.getAddress1());
            userProfile.setAddress2(updatedProfile.getAddress2());
            userProfile.setCity(updatedProfile.getCity());
            userProfile.setState(updatedProfile.getState());
            userProfile.setZip(updatedProfile.getZip());
            userProfile.setPhone(updatedProfile.getPhone());
            repository.saveAndFlush(userProfile);
        }
    }

    private void populateUserProfile(UserProfile userProfile) {
        userProfile = new UserProfile("", "", "", "", "");
    }
}
