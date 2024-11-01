package com.example.app.service;


import com.example.app.model.User;

import java.util.Optional;

public interface UserService {

    User save(User user);

    Optional<User> findByUsername(String username);
}
