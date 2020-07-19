package com.example.SecuritySpringDataSpecifications.controllers;

import com.example.SecuritySpringDataSpecifications.Services.DAOUserServiceImlementation;
import com.example.SecuritySpringDataSpecifications.models.AuthenticationRequest;
import com.example.SecuritySpringDataSpecifications.models.AuthenticationResponse;
import com.example.SecuritySpringDataSpecifications.models.User;
import com.example.SecuritySpringDataSpecifications.security.jwt.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/authentcaiton")
public class AuthenticationController {

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    DAOUserServiceImlementation daoUserServiceImlementation;

    @PostMapping()
    public ResponseEntity authenticateTheUser(@RequestBody AuthenticationRequest authenticationRequest)
            throws BadCredentialsException {
        try {
            String username = authenticationRequest.getUsername();
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    username, authenticationRequest.getPassword()));

            User user = daoUserServiceImlementation.
                findUserByUsername(authenticationRequest.getUsername());

            String token = jwtTokenProvider.createToken(user.getUsername(), user.getRoles());

            return ResponseEntity.ok(new AuthenticationResponse(token));
        } catch (AuthenticationException e) {
            throw new BadCredentialsException("bad credentials");
        }
    }
}
