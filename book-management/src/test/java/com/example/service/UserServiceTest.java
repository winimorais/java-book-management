package com.example.service;

import com.example.app.model.User;
import com.example.app.repository.UserRepository;
import com.example.app.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSave() {
        User user = new User();
        when(userRepository.save(user)).thenReturn(user);

        User savedUser = userService.save(user);

        assertNotNull(savedUser);
        verify(userRepository, times(1)).save(user);
    }

    @Test
    void testFindByUsername() {
        String username = "testuser";
        User user = new User();
        when(userRepository.findByUsername(username)).thenReturn(Optional.of(user));

        Optional<User> result = userService.findByUsername(username);

        assertTrue(result.isPresent());
        assertEquals(user, result.get());
        verify(userRepository, times(1)).findByUsername(username);
    }

    @Test
    void testFindByUsername_NotFound() {
        String username = "testuser";
        when(userRepository.findByUsername(username)).thenReturn(Optional.empty());

        Optional<User> result = userService.findByUsername(username);

        assertFalse(result.isPresent());
        verify(userRepository, times(1)).findByUsername(username);
    }
}
