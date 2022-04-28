package bae.project.api.security;

import bae.project.api.domain.User;
import bae.project.api.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserPrincipalDetailsService implements UserDetailsService {

    private UserRepo userRepo;

    public UserPrincipalDetailsService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = this.userRepo.findByUsername(username);
        if (user == null)
        {
            System.out.println(""+username);
            throw new UsernameNotFoundException("user not found");
        }

        return new UserPrincipal(user);
    }
}
