package com.taskflow.taskmanagement.services.validations;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validation;
import jakarta.validation.Validator;

import java.util.Collections;
import java.util.Set;

public abstract class BaseValidation {

    protected  <O> void validateObject(O object) {

        Validation.buildDefaultValidatorFactory()
                .getValidator()
                .validate(object)
                .stream()
                .findFirst()
                .ifPresent(violation -> {
                    throw new ConstraintViolationException(Set.of(violation));
                });

    }
}
