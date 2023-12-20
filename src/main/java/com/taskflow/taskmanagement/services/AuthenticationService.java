package com.taskflow.taskmanagement.services;

import com.taskflow.taskmanagement.dtos.authentication.request.SignUpRequest;
import com.taskflow.taskmanagement.dtos.authentication.request.SignInRequest;
import com.taskflow.taskmanagement.dtos.authentication.response.JwtAuthenticationResponse;

public interface AuthenticationService {
    JwtAuthenticationResponse signUp(SignUpRequest request);

    JwtAuthenticationResponse signIn(SignInRequest request);
}
