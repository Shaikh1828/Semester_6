package com.example.usermanagement.application;

import com.example.usermanagement.application.interfaces.RoleRepository;
import com.example.usermanagement.domain.Role;

import org.junit.Before;
import org.junit.Test;
import org.mockito.*;

import java.util.*;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class RoleServiceTest {

    @Mock
    private RoleRepository roleRepo;

    @InjectMocks
    private RoleService roleService;

    private UUID roleId;
    private Role role;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        roleId = UUID.randomUUID();
        role = new Role(roleId, "Admin");

        when(roleRepo.save(any(Role.class))).thenReturn(role);
    }

    @Test
    public void testCreateRole() {
        UUID id = roleService.createRole("Admin");

        assertNotNull(id);
        verify(roleRepo).save(any(Role.class));
    }
}