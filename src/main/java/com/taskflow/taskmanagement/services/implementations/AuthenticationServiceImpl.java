package com.taskflow.taskmanagement.services.implementations;

import com.taskflow.taskmanagement.dtos.authentication.request.SignUpRequest;
import com.taskflow.taskmanagement.dtos.authentication.request.SignInRequest;
import com.taskflow.taskmanagement.dtos.authentication.response.JwtAuthenticationResponse;
import com.taskflow.taskmanagement.embeddables.FullName;
import com.taskflow.taskmanagement.embeddables.Password;
import com.taskflow.taskmanagement.entities.Role;
import com.taskflow.taskmanagement.entities.User;
import com.taskflow.taskmanagement.services.*;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UserService userService;

    private final JwtService jwtService;

    private final AuthenticationManager authenticationManager;

    private final RoleService roleService;

    private final CardService cardService;

    @Override
    public JwtAuthenticationResponse signUp(SignUpRequest request) {

        // Build a user object from the signup request
        User user = buildUser(request);

        // Create the user in the database
        userService.createUser(user);

        // Create default jetons
        cardService.createDefaultCards(user);

        // Generate a JWT token for the registered user
        String jwt = jwtService.generateToken(user);

        // Return the JWT token in the response
        return JwtAuthenticationResponse.builder().token(jwt).build();
    }

    @Override
    public JwtAuthenticationResponse signIn(SignInRequest request) {
        // Authenticate the user using the provided credentials
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));

        // Retrieve the user details from the database
        User user = userService.findByUsername(request.getUsername());

        // Generate a JWT token for the authenticated user
        String jwt = jwtService.generateToken(user);

        // Return the JWT token in the response
        return JwtAuthenticationResponse.builder().token(jwt).build();
    }

    private User buildUser(SignUpRequest request) {

        Role MEMBER = roleService.getRoleByName("MEMBER");

        // Build a User object from the SignUpRequest
        return User.builder()
                .username(request.getUsername())
                .name(new FullName(request.getFirstName(), request.getMiddleName(), request.getLastName()))
                .email(request.getEmail())
                .password(new Password(request.getPassword()))
                .roles(List.of(MEMBER))  // --- Set the default role (MEMBER)
                .build();
    }
}
