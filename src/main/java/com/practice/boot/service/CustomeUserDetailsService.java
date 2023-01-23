package com.practice.boot.service;

import com.practice.boot.exception.UserNotFoundException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
@Service
public class CustomeUserDetailsService implements UserDetailsService {
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if ("Mudassir".equals(username)) {
            return new User("Mudassir", "$2a$12$uDM5cYySM1QHLNKELnHBdeV9w.u7GXoR.I4NMmT/DrrBi9vELKi7e",
                    new ArrayList<>());

            //pswd Mudassir123--needs to bcrytp it
        } else {
            System.out.println("*********** USER NOT FOUND ***************");
            throw new UserNotFoundException("User not found with username: " + username + "");
        }
    }
}
