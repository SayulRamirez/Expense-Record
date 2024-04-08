package edu.project.validations;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class DateFormatValidator implements ConstraintValidator<ValidDateFormat, String> {

    @SuppressWarnings("all")
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {

        if (value == null) return false;

        try {
            LocalDate.parse(value);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }
}
