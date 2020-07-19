package com.example.SecuritySpringDataSpecifications.repositories;

import com.example.SecuritySpringDataSpecifications.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long>, JpaSpecificationExecutor<User> {

    @Query(value = "SELECT * FROM users WHERE username = :passedUsername", nativeQuery = true)
    Optional<User> findByUsername(@Param("passedUsername") String username);
}
