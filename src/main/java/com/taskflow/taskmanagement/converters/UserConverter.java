package com.taskflow.taskmanagement.converters;

import com.taskflow.taskmanagement.dtos.authentication.response.UserResponse;
import com.taskflow.taskmanagement.entities.User;
import org.springframework.stereotype.Component;

@Component
public class UserConverter {

    public UserResponse convertToUserResponse(User user) {

        return UserResponse.builder()
                .email(user.getEmail())
                .name(user.getName())
                .roles(user.getRoles())
                .authorities(user.getAuthorities())
                .cards(user.getCards())
                .build();

    }
}
