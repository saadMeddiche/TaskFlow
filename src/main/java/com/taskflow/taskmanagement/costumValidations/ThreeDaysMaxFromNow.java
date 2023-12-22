package com.taskflow.taskmanagement.costumValidations;


import jakarta.validation.Constraint;
import jakarta.validation.Payload;


import java.lang.annotation.*;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ThreeDaysMaxFromNowValidator.class)
@Documented
public @interface ThreeDaysMaxFromNow {
    String message() default "Date must be within 3 days from now";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
