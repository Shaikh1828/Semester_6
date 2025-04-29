package com.example.usermanagement.application;

import com.example.usermanagement.application.interfaces.RoleRepository;
import com.example.usermanagement.domain.Role;
import java.util.*;

public class RoleService {
    private final RoleRepository roleRepo;

    public RoleService(RoleRepository roleRepo) {
        this.roleRepo = roleRepo;
    }

    public UUID createRole(String roleName) {
        Role role = new Role(UUID.randomUUID(), roleName);
        return roleRepo.save(role).getId();
    }
}
