package com.taskflow.taskmanagement.controller;

import com.taskflow.taskmanagement.dtos.authentication.request.SignInRequest;
import com.taskflow.taskmanagement.dtos.authentication.request.SignUpRequest;
import com.taskflow.taskmanagement.dtos.authentication.response.JwtAuthenticationResponse;
import com.taskflow.taskmanagement.services.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/authentication")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;
    @PostMapping("/signUp")
    public ResponseEntity<JwtAuthenticationResponse> signUp(@RequestBody SignUpRequest request) {
        JwtAuthenticationResponse response = authenticationService.signUp(request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/signIn")
    public ResponseEntity<JwtAuthenticationResponse> signIn(@RequestBody SignInRequest request) {
        authenticationService.signIn(request);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
