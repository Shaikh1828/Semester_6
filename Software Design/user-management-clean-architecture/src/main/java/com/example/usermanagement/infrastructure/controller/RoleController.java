package com.example.usermanagement.infrastructure.controller;

import com.example.usermanagement.application.RoleService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/roles")
public class RoleController {

    private final RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @PostMapping
    public ResponseEntity<Map<String, UUID>> createRole(@Valid @RequestBody CreateRoleRequest request) {
        UUID id = roleService.createRole(request.roleName());
        return ResponseEntity.ok(Map.of("roleId", id));
    }

    public record CreateRoleRequest(@NotBlank String roleName) {}
}
