package com.taskflow.taskmanagement.validations;


import jakarta.validation.Constraint;


import java.lang.annotation.*;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ThreeDaysMaxFromNowValidator.class)
@Documented
public @interface ThreeDaysMaxFromNow {
    String message() default "Date must be within 3 days from now";

}
