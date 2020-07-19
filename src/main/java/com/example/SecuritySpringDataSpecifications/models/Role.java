package com.example.SecuritySpringDataSpecifications.models;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@Data
@Table(name = "roles")
public class Role extends BaseEntity {

    private String name;

    @ManyToMany(mappedBy = "roles", fetch = FetchType.EAGER)
    private List<User> userList;
}
