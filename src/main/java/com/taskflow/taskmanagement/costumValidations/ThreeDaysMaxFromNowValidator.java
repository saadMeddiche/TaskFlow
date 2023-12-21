package com.taskflow.taskmanagement.costumValidations;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class ThreeDaysMaxFromNowValidator implements ConstraintValidator<ThreeDaysMaxFromNow, LocalDate> {

    @Override
    public boolean isValid(LocalDate date, ConstraintValidatorContext context) {

        if (date == null) {
            context.disableDefaultConstraintViolation();

            context.buildConstraintViolationWithTemplate("The Date Cannot Be Null")
                    .addConstraintViolation();
        }

        LocalDate currentDate = LocalDate.now();

        long daysDifference = ChronoUnit.DAYS.between(currentDate, date);

        return daysDifference <= 3;
    }

}
