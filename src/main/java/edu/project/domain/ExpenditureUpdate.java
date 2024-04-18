package edu.project.domain;

import edu.project.validations.ValidDateFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public record ExpenditureUpdate(

        @Schema(example = "3")
        @NotNull(message = "The field cannot be null")
        @Positive(message = "Must be greater then 0")
        Long id,

        @Schema(example = "Corte de pelo a el firulais en zona norte")
        @Size(min = 5, max = 50, message = "Only between 5 and 50 characters")
        @NotBlank(message = "The field cannot be null or blank")
        String title,

        @Schema(example = "Lo tenia muy largo el pelo, parecia mechudo")
        @Size(min = 5, max = 100, message = "Only between 5 and 10 characters")
        @NotBlank(message = "The field cannot be null or blank")
        String description,

        @Schema(example = "250.0")
        @Positive(message = "Must be greater than 0")
        @NotNull(message = "The field cannot be null")
        Double amount,

        @Schema(example = "2024-01-15")
        @ValidDateFormat
        String date,

        @Schema(example = "MASCOTAS")
        @NotBlank(message = "The field cannot be null or blank")
        String category
) {
}
