package com.example.car_show.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository repo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = checkOptionalUser(username);
        // var builder = org.springframework.security.core.userdetails.User.withUsername(user.getEmailAddress());
        // builder.password(user.getPassword());
        // builder.roles(user.getRole());
        // return builder.build();

        return org.springframework.security.core.userdetails.User.builder()
            .username(user.getEmailAddress())
            .password(user.getPassword())
            .roles(user.getRole())
            .build();
    }

    private User checkOptionalUser(String emailAddress) {
        return repo.findUserByEmailAddressIgnoringCase(emailAddress)
            .orElseThrow(() -> new UsernameNotFoundException("No user with the email " + emailAddress + " was found."));
    }
    
}
