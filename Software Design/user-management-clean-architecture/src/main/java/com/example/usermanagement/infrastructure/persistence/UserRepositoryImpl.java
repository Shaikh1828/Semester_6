package com.example.usermanagement.infrastructure.persistence;

import com.example.usermanagement.application.interfaces.UserRepository;
import com.example.usermanagement.domain.*;
import java.util.*;
import java.util.stream.Collectors;

public class UserRepositoryImpl implements UserRepository {

    private final UserJpaRepository repo;

    public UserRepositoryImpl(UserJpaRepository repo) {
        this.repo = repo;
    }

    @Override
    public User save(User user) {
        UserJpaEntity entity = new UserJpaEntity();
        entity.setId(user.getId());
        entity.setName(user.getName());
        entity.setEmail(user.getEmail());
        List<RoleJpaEntity> roleEntities = user.getRoles().stream().map(r -> {
            RoleJpaEntity roleEntity = new RoleJpaEntity();
            roleEntity.setId(r.getId());
            roleEntity.setRoleName(r.getRoleName());
            return roleEntity;
        }).collect(Collectors.toList());
        entity.setRoles(roleEntities);
        UserJpaEntity saved = repo.save(entity);
        return new User(saved.getId(), saved.getName(), saved.getEmail());
    }

    @Override
    public Optional<User> findById(UUID id) {
        return repo.findById(id).map(entity -> {
            User user = new User(entity.getId(), entity.getName(), entity.getEmail());
            entity.getRoles().forEach(role -> user.addRole(new Role(role.getId(), role.getRoleName())));
            return user;
        });
    }
}
