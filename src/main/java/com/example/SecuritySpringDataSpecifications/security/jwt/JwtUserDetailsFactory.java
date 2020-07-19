package com.example.SecuritySpringDataSpecifications.security.jwt;

import com.example.SecuritySpringDataSpecifications.models.Role;
import com.example.SecuritySpringDataSpecifications.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.stream.Collectors;

public class JwtUserDetailsFactory {

    @Autowired
    private static PasswordEncoder passwordEncoder;

    private JwtUserDetailsFactory() {

    }

    public static JWTUserDetailsImplmentation createNewInstanceOfTheJwtUserDetailsFromUserObject(User user) {
        return new JWTUserDetailsImplmentation(
                user.getId(),
                passwordEncoder.encode(user.getPassword()),
                user.getUsername(),
                user.getEmail(),
                user.getFirstName(),
                user.getFirstName(),
                convertRoleToGrantedAuthorities(user.getRoles())
        );
    }

    private static List<? extends GrantedAuthority> convertRoleToGrantedAuthorities(List<Role> roles) {
        return roles.stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toList());
    }
}
