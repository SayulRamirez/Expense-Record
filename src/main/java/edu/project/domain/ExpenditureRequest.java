package edu.project.domain;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

public record ExpenditureRequest(

        @Size(min = 5, max = 50, message = "Only between 5 and 50 characters")
        @NotBlank(message = "The field cannot be null o blank")
        String title,

        @Size(min = 5, max = 100, message = "Only between 5 and 10 characters")
        @NotBlank(message = "The field cannot be null o blank")
        String description,

        @Positive(message = "Must be greater than 0")
        Double amount,

        @DateTimeFormat(pattern = "^\\d{4}-(0?[1-9]|1[012])-(0?[1-9]|[12][0-9]|3[01])$")
        LocalDate date,

        String category
) {
}
