package edu.project.domain;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDate;

public record ExpenditureResponse(

        @Schema(example = "3")
        Long id,

        @Schema(example = "Corte de pelo a firulais")
        String title,

        @Schema(example = "Gasto generado por el corte de pelo, ya estaba demasiado largo")
        String description,

        @Schema(example = "289.99")
        Double amount,

        @Schema(example = "2024-04-12")
        LocalDate date,

        @Schema(example = "MASCOTAS")
        String category
) {
}
