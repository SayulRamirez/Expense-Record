package edu.project.services;

import edu.project.domain.ExpenditureRequest;
import edu.project.domain.ExpenditureResponse;

import java.time.LocalDate;

public interface ExpenditureService {
    ExpenditureResponse register(ExpenditureRequest request, LocalDate date);
}
