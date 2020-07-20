package com.example.SecuritySpringDataSpecifications.services;

import com.example.SecuritySpringDataSpecifications.models.User;
import com.example.SecuritySpringDataSpecifications.repositories.UserServiceDAOImlementation;
import com.example.SecuritySpringDataSpecifications.security.jwt.JwtUserDetailsFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service(value = "implementedServiceBean")
@Slf4j
public class UserDetailServiceImplementation implements UserDetailsService {

    @Autowired
    @Qualifier(value = "BeanFromSuperClass")
    private UserDetailsService daoUserServiceImlementation;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            UserDetails fetchedUser = daoUserServiceImlementation.loadUserByUsername(username);
            if (fetchedUser == null) {
                log.warn("User with username {} was not found", username);
                throw new UsernameNotFoundException(String.format("User with username %s was not found", username));
            }
            log.info("User with username {} was successful find", username);
//            return JwtUserDetailsFactory.createNewInstanceOfTheJwtUserDetailsFromUserObject(fetchedUser);
            return fetchedUser;
        } catch (NoSuchElementException exception) {
            throw new UsernameNotFoundException(String.format("User with username %s was not found", username));
        }
    }
}
