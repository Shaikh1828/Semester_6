package com.example.usermanagement.infrastructure.persistence;

import jakarta.persistence.*;
import java.util.*;

@Entity
public class UserJpaEntity {
    @Id
    private UUID id;
    private String name;
    private String email;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<RoleJpaEntity> roles = new ArrayList<>();

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public List<RoleJpaEntity> getRoles() { return roles; }
    public void setRoles(List<RoleJpaEntity> roles) { this.roles = roles; }
}
