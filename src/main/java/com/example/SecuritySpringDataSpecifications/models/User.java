package com.example.SecuritySpringDataSpecifications.models;

import lombok.Data;
import org.hibernate.annotations.ManyToAny;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Table(name = "users")
public class User extends BaseEntity{

    private String firstName;
    private String lastName;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;
    private String email;

    @ManyToMany
    @JoinTable(name = "user_roles", joinColumns =
                                  {@JoinColumn(name = "user_id", referencedColumnName = "id")},
                          inverseJoinColumns =
                                  {@JoinColumn(name = "role_id", referencedColumnName = "id")})
    private List<Role> roles;
}
