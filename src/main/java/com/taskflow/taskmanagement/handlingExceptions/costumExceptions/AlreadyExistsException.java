package com.taskflow.taskmanagement.handlingExceptions.costumExceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AlreadyExistsException extends RuntimeException {
    private final String error;
}