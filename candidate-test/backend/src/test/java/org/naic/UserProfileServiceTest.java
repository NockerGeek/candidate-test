package org.naic;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.naic.data.UserProfileRepository;
import org.naic.model.UserProfile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@Transactional
@SpringBootTest
public class UserProfileServiceTest {

    private static final Logger LOG = LoggerFactory.getLogger(UserProfileServiceTest.class);

    @Autowired
    UserProfileRepository repository;
    UserProfile user;

    @Before
    public void setUp() {

        user = new UserProfile();
        user.setUsername("test");
        user.setPassword("test");
        user.setFirstName("test");
        user.setLastName("tester");
        user.setEmail("test@test.com");
    }

    @Test
    public void findSavedUserById() {

        user = repository.save(user);

        LOG.info(user.toString());

        assertThat(repository.findOne(user.getId()), is(user));
    }

    @Test
    public void findUserByUsername() {

        user = repository.save(user);

        LOG.info(user.toString());

        assertThat(repository.findByUsername(user.getUsername()), is(user));
    }

    @Test
    public void updateUser() {

        user = repository.save(user);

        assertThat(repository.findOne(user.getId()), is(user));

        LOG.info(user.toString());

        assertNull(user.getZip());
        assertNull(user.getPhone());

        long id = user.getId();

        user.setZip("12345");
        user.setPhone("1234567890");

        repository.saveAndFlush(user);

        user = repository.getOne(id);

        LOG.info(user.toString());

        assertEquals(id, user.getId());
        assertEquals("12345", user.getZip());
        assertEquals("1234567890", user.getPhone());
        assertEquals("test@test.com", user.getEmail());

    }

    @Test
    public void checkUniqueValidation() {

        user = repository.save(user);

        assertThat(repository.findOne(user.getId()), is(user));

        LOG.info(user.toString());

        long id = user.getId();

        UserProfile duplicate = new UserProfile();
        duplicate.setFirstName(user.getFirstName());
        duplicate.setLastName(user.getLastName());
        duplicate.setUsername(user.getUsername());
        duplicate.setPassword(user.getPassword());
        duplicate.setEmail(user.getEmail());

        String error = null;

        try {
            duplicate = repository.save(duplicate);
        } catch (Exception e) {
            error = e.getMessage();
        }

        LOG.info(error);

        assertNotNull(error);
        assertEquals(0L, duplicate.getId());
    }

}

