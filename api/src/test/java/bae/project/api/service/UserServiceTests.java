package bae.project.api.service;

import bae.project.api.domain.Factoid;
import bae.project.api.domain.User;
import bae.project.api.repo.FactoidRepo;
import bae.project.api.repo.UserRepo;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserServiceTests {
    @Autowired
    private UserService service;

    @MockBean
    private UserRepo repo;

    User testUser = new User("test123","pass1",1,"USER");
    User testUserId = new User(2L,"test123","pass1",1,"USER");

    @Test
    public void createTest() {

        Mockito.when(this.repo.saveAndFlush(testUser)).thenReturn(testUserId);

        assertEquals(testUserId, this.service.create(testUser));

        Mockito.verify(this.repo, Mockito.times(1)).saveAndFlush(testUser);
    }

    @Test
    public void getByIdTest() {
        Optional<User> OptionalOutput = Optional.of(testUserId);


        Mockito.when(this.repo.findById(2L)).thenReturn(OptionalOutput);

        assertEquals(testUserId, this.service.getById(2L));

        Mockito.verify(this.repo, Mockito.times(1)).findById(2L);
    }

    @Test
    public void getByUsername() {

        Mockito.when(this.repo.findByUsername("test123")).thenReturn(testUserId);

        assertEquals(testUserId, this.service.findByUsername("test123"));

        Mockito.verify(this.repo, Mockito.times(1)).findByUsername("test123");
    }


}
