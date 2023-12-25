package com.taskflow.taskmanagement.dtos.authentication.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.taskflow.taskmanagement.embeddables.FullName;
import com.taskflow.taskmanagement.entities.Card;
import com.taskflow.taskmanagement.entities.Role;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;


import java.util.Collection;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse {

    private String email;

    private FullName name;

    @JsonIgnoreProperties({"users" , "permissions"} )
    private List<Role> roles;

    private Collection<? extends GrantedAuthority> authorities;

    @JsonIgnoreProperties("user")
    private List<Card> cards;
}
