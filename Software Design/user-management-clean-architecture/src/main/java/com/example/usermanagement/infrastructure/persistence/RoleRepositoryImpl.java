package com.example.usermanagement.infrastructure.persistence;

import com.example.usermanagement.application.interfaces.RoleRepository;
import com.example.usermanagement.domain.Role;
import java.util.Optional;
import java.util.UUID;

public class RoleRepositoryImpl implements RoleRepository {

    private final RoleJpaRepository repo;

    public RoleRepositoryImpl(RoleJpaRepository repo) {
        this.repo = repo;
    }

    @Override
    public Role save(Role role) {
        RoleJpaEntity entity = new RoleJpaEntity();
        entity.setId(role.getId());
        entity.setRoleName(role.getRoleName());
        RoleJpaEntity saved = repo.save(entity);
        return new Role(saved.getId(), saved.getRoleName());
    }

    @Override
    public Optional<Role> findById(UUID id) {
        return repo.findById(id).map(e -> new Role(e.getId(), e.getRoleName()));
    }
}
