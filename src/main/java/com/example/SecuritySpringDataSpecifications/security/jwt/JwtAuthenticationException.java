package com.example.SecuritySpringDataSpecifications.security.jwt;

import javax.naming.AuthenticationException;

public class JwtAuthenticationException extends AuthenticationException {

    public JwtAuthenticationException(String explanation) {
        super(explanation);
    }

    public JwtAuthenticationException() {
        super();
    }
}
