package com.example.SecuritySpringDataSpecifications.Services;

import com.example.SecuritySpringDataSpecifications.models.User;
import com.example.SecuritySpringDataSpecifications.security.jwt.JwtUserDetailsFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
@Slf4j
public class UserDetailServiceImplementation implements UserDetailsService {

    @Autowired
    private DAOUserServiceImlementation daoUserServiceImlementation;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            User fetchedUser = daoUserServiceImlementation.findUserByUsername(username);
            if (fetchedUser == null) {
                log.warn("User with username {} was not found", username);
                throw new UsernameNotFoundException(String.format("User with username %s was not found", username));
            }
            log.info("User with username {} was successful find", username);
            return JwtUserDetailsFactory.createNewInstanceOfTheJwtUserDetailsFromUserObject(fetchedUser);
        } catch (NoSuchElementException exception) {
            throw new UsernameNotFoundException(String.format("User with username %s was not found", username));
        }
    }
}
