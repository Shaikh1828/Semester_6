package com.example.usermanagement.domain;

import java.util.*;

public class User {
    private UUID id;
    private String name;
    private String email;
    private List<Role> roles = new ArrayList<>();

    public User(UUID id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    public void addRole(Role role) {
        this.roles.add(role);
    }

    public UUID getId() { return id; }
    public String getName() { return name; }
    public String getEmail() { return email; }
    public List<Role> getRoles() { return roles; }
}
