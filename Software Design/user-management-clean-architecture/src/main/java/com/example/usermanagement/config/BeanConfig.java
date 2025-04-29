package com.example.usermanagement.config;

import com.example.usermanagement.application.*;
import com.example.usermanagement.application.interfaces.*;
import com.example.usermanagement.infrastructure.persistence.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfig {

    @Bean
    public UserService userService(UserRepository userRepository, RoleRepository roleRepository) {
        return new UserService(userRepository, roleRepository);
    }

    @Bean
    public RoleService roleService(RoleRepository roleRepository) {
        return new RoleService(roleRepository);
    }

    @Bean
    public UserRepository userRepository(UserJpaRepository repo) {
        return new UserRepositoryImpl(repo);
    }

    @Bean
    public RoleRepository roleRepository(RoleJpaRepository repo) {
        return new RoleRepositoryImpl(repo);
    }
}
