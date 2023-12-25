package com.taskflow.taskmanagement.dtos.authentication.response;

import com.taskflow.taskmanagement.embeddables.FullName;
import com.taskflow.taskmanagement.entities.Card;
import com.taskflow.taskmanagement.entities.Role;


import java.util.List;

public class UserResponse {

    private String email;

    private FullName name;

    private List<Role> roles;

    private List<String> authorities;

    private List<Card> cards;
}
