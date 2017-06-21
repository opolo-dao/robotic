package lv.challenge.servlets.security;

import lv.challenge.domain.users.User;
import lv.challenge.services.interfaces.CompetitorService;
import lv.challenge.services.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

/**
 * Created by Daniil on 12.06.2017.
 */
public class MyUserDetailService implements UserDetailsService {
    @Autowired
    private CompetitorService<User> userService;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Optional<User> userOptional = ((UserService)userService).findByLogin(s);
        if(userOptional.isPresent()){
            return new MyUserDetail(userOptional.get());
        }
        else
            throw new UsernameNotFoundException("User with this login not exist");
    }
}
