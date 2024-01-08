package Reporting.AFA.Security.Services;

import Reporting.AFA.Entity.AppUser;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private AccountService accountService;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser user=accountService.loadUserByUsername(username);
        if(user==null) throw new UsernameNotFoundException(String.format("Utilisateur %s non identifiée",username));


        String[] roles=user.getRoles().stream().map(u -> u.getRole()).toArray(String[]::new);
        UserDetails userDetails= User
                .withUsername(user.getUsername())
                .password(user.getPassword())
                .roles(roles).build();
        return userDetails;
    }
}
