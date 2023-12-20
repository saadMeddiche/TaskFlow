package com.taskflow.taskmanagement.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/resource")
@RequiredArgsConstructor
public class AuthorizationController {
    @GetMapping("/home")
    public ResponseEntity<String> home() {
        return ResponseEntity.ok("This is Home Page , No need to authenticate");
    }

    @GetMapping("/account")
    public ResponseEntity<String> account() {
        return ResponseEntity.ok("This is the profile page , Need to authenticate");
    }

    @GetMapping("/admin")
    public ResponseEntity<String> admin() {
        return ResponseEntity.ok("This is the admin page , Need to authenticate , and have Authority * ( '*' means access to all)");
    }

}
