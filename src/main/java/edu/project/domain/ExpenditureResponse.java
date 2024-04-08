package edu.project.domain;

import java.time.LocalDate;

public record ExpenditureResponse(

        String title,
        String description,
        Double amount,
        LocalDate date,
        String category
) {
}
