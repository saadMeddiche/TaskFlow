package com.taskflow.taskmanagement.services.validations;

import jakarta.validation.*;

import java.util.Collections;
import java.util.Set;
import java.util.function.Predicate;

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

    protected  <T, E extends Exception> void throwExceptionIf(Predicate<T> predicate, T value, ExceptionSupplier<E> exceptionSupplier, String message) throws E {
        if (predicate.test(value)) {
            throw exceptionSupplier.get(message);
        }
    }

    @FunctionalInterface
    interface ExceptionSupplier<E extends Exception> {
        E get(String message);
    }
}
