package com.example.SecuritySpringDataSpecifications.repositories;

import com.example.SecuritySpringDataSpecifications.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {

    @Query(value = "SELECT * FROM roles WHERE name = ?1", nativeQuery = true)
    Optional<Role> findRoleByName(String name);
}
