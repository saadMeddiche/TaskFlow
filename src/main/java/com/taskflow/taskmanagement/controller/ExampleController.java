package com.taskflow.taskmanagement.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/V1/example")
public class ExampleController {

    @GetMapping("/freeAccess")
    public ResponseEntity<?> example() {
        return new ResponseEntity<>("Free Access Page" ,HttpStatus.OK);
    }

    @GetMapping("/tiedAccess")
    public ResponseEntity<?> tiedAccess() {
        return new ResponseEntity<>("Tied Access Page" ,HttpStatus.OK);
    }
}
