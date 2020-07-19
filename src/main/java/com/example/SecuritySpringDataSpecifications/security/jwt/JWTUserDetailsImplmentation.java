package com.example.SecuritySpringDataSpecifications.security.jwt;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

public class JWTUserDetailsImplmentation implements UserDetails {

    private final Long id;
    private final String password;
    private final String username;
    private final String email;
    private final String firstName;
    private final String lastName;
    private final Collection<? extends GrantedAuthority> authorities;

    public JWTUserDetailsImplmentation(Long id, String password, String username, String email, String firstName,
                                       String lastName, Collection<? extends GrantedAuthority> authorities) {
        this.id = id;
        this.password = password;
        this.username = username;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.authorities = authorities;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @JsonIgnore
    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {
        return null;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @JsonIgnore
    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}
