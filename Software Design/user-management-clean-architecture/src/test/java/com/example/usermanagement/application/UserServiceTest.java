package com.example.usermanagement.application;

import com.example.usermanagement.application.interfaces.UserRepository;
import com.example.usermanagement.application.interfaces.RoleRepository;
import com.example.usermanagement.domain.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.*;

import java.util.*;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class UserServiceTest {

    @Mock
    private UserRepository userRepo;

    @Mock
    private RoleRepository roleRepo;

    @InjectMocks
    private UserService userService;

    private UUID userId;
    private UUID roleId;
    private User user;
    private Role role;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        userId = UUID.randomUUID();
        roleId = UUID.randomUUID();
        user = new User(userId, "Shaikh", "Shaikh@gmail.com");
        role = new Role(roleId, "Student");

        when(userRepo.save(any(User.class))).thenReturn(user);
        when(userRepo.findById(userId)).thenReturn(Optional.of(user));
        when(roleRepo.findById(roleId)).thenReturn(Optional.of(role));
    }

    @org.junit.Test
    public void testCreateUser() {
        UUID id = userService.createUser("Shaikh", "Shaikh@gmail.com");

        assertNotNull(id);
        verify(userRepo, times(1)).save(any(User.class));
    }

    @org.junit.Test
    public void testAssignRoleToUser() {
        userService.assignRoleToUser(userId, roleId);

        verify(userRepo).save(user);
    }

    @org.junit.Test
    public void testGetUserDetails() {
        User found = userService.getUserDetails(userId);

        assertEquals("Shaikh", found.getName());
        assertEquals("Shaikh@gmail.com", found.getEmail());
        verify(userRepo).findById(userId);
    }
}