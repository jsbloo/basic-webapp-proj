package bae.project.api.service;

import bae.project.api.domain.Factoid;
import bae.project.api.domain.User;
import bae.project.api.exceptions.FactoidNotFoundException;
import bae.project.api.repo.UserRepo;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
public class UserService {

    private UserRepo repo;
    private PasswordEncoder passwordEncoder;

    public UserService(UserRepo repo, PasswordEncoder passwordEncoder) {
        this.repo = repo;
        this.passwordEncoder = passwordEncoder;
    }

    public User create(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return repo.saveAndFlush(user);}

    public User getById(long id){return repo.findById(id).orElseThrow(EntityNotFoundException::new);}

    public User findByUsername(String username){
        return repo.findByUsername(username);
    }

}
