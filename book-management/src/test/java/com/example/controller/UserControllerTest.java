package com.example.controller;

import com.example.app.controller.UserController;
import com.example.app.model.User;
import com.example.app.service.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class UserControllerTest {

    @Mock
    private UserServiceImpl userService;

    @InjectMocks
    private UserController userController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testRegister() {
        User user = new User();
        user.setId("550e8400-e29b-41d4-a716-446655440000");
        user.setUsername("testuser");
        user.setPassword("password");
        user.setEmail("testuser@example.com");

        when(userService.save(any(User.class))).thenReturn(user);

        User savedUser = userController.register(user);

        assertNotNull(savedUser);
        assertEquals("testuser", savedUser.getUsername());
        assertEquals("testuser@example.com", savedUser.getEmail());
        verify(userService, times(1)).save(user);
    }

    @Test
    void testGetUser() {
        String username = "testuser";
        User user = new User();
        user.setId("550e8400-e29b-41d4-a716-446655440000");
        user.setUsername(username);
        user.setPassword("password");
        user.setEmail("testuser@example.com");

        when(userService.findByUsername(username)).thenReturn(Optional.of(user));

        Optional<User> result = userController.getUser(username);

        assertTrue(result.isPresent());

    }
}