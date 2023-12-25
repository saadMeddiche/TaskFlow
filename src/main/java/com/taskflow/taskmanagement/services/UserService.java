package com.taskflow.taskmanagement.services;

import com.taskflow.taskmanagement.entities.User;
import jakarta.validation.Valid;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService {
    UserDetailsService userDetailsService();

    User findByUsername(String username);

    User getByEmail(String email);

    User createUser(User user);
}
