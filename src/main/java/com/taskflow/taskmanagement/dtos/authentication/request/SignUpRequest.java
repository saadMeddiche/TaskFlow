package com.taskflow.taskmanagement.dtos.authentication.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SignUpRequest {
    private String username;
    private String firstName;
    private String middleName;
    private String lastName;
    private String email;
    private String password;
}
