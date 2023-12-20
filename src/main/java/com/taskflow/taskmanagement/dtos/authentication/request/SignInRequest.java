package com.taskflow.taskmanagement.dtos.authentication.request;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SignInRequest {
    private String username;
    private String password;
}
