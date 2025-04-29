package com.example.usermanagement.application;

import com.example.usermanagement.application.interfaces.*;
import com.example.usermanagement.domain.*;
import java.util.*;

public class UserService {
    private final UserRepository userRepo;
    private final RoleRepository roleRepo;

    public UserService(UserRepository userRepo, RoleRepository roleRepo) {
        this.userRepo = userRepo;
        this.roleRepo = roleRepo;
    }

    public UUID createUser(String name, String email) {
        User user = new User(UUID.randomUUID(), name, email);
        return userRepo.save(user).getId();
    }

    public void assignRoleToUser(UUID userId, UUID roleId) {
        User user = userRepo.findById(userId).orElseThrow();
        Role role = roleRepo.findById(roleId).orElseThrow();
        user.addRole(role);
        userRepo.save(user);
    }

    public User getUserDetails(UUID userId) {
        return userRepo.findById(userId).orElseThrow();
    }
}
