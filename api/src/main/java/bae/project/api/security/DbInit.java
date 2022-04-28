package bae.project.api.security;

import bae.project.api.domain.User;
import bae.project.api.repo.UserRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class DbInit implements CommandLineRunner {
    private UserRepo userRepo;
    private PasswordEncoder passwordEncoder;

    public DbInit(UserRepo userRepo, PasswordEncoder passwordEncoder) {
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) {
        // Delete all
        this.userRepo.deleteAll();

        // Crete users
        User admin = new User("admin",passwordEncoder.encode("admin123"),1,"ADMIN");
        User dan = new User("dan",passwordEncoder.encode("dan123"),1,"USER");

        List<User> users = Arrays.asList(dan);

        // Save to db
        this.userRepo.saveAll(users);
    }
}