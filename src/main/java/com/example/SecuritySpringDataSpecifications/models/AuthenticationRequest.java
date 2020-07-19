package com.example.SecuritySpringDataSpecifications.models;

import lombok.Data;

@Data
public class AuthenticationRequest {

    private String username;
    private String password;
}
