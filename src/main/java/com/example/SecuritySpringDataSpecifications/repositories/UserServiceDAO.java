package com.example.SecuritySpringDataSpecifications.repositories;

import com.example.SecuritySpringDataSpecifications.models.User;

import java.util.List;

public interface UserServiceDAO {

    List<User> findAllUsers();

    void registerUser(User user);

    User findUserByUsername(String username);

    void deleteUser(Long id);

    User findUserById(Long id);
}
