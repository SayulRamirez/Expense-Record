package edu.project.domain;

import edu.project.validations.ValidDateFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public record ExpenditureUpdate(

        @NotNull(message = "The field cannot be null")
        @Positive(message = "Must be greater then 0")
        Long id,

        @Size(min = 5, max = 50, message = "Only between 5 and 50 characters")
        @NotBlank(message = "The field cannot be null or blank")
        String title,

        @Size(min = 5, max = 100, message = "Only between 5 and 10 characters")
        @NotBlank(message = "The field cannot be null or blank")
        String description,

        @Positive(message = "Must be greater than 0")
        @NotNull(message = "The field cannot be null")
        Double amount,

        @ValidDateFormat
        String date,

        @NotBlank(message = "The field cannot be null or blank")
        String category
) {
}
