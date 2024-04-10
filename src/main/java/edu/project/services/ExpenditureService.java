package edu.project.services;

import edu.project.domain.ExpenditureRequest;
import edu.project.domain.ExpenditureResponse;
import edu.project.domain.ExpenditureUpdate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;

public interface ExpenditureService {
    ExpenditureResponse register(ExpenditureRequest request, LocalDate date);

    ExpenditureResponse edit(ExpenditureUpdate request, LocalDate date);

    Page<ExpenditureResponse> findAll(Pageable pageable);

    Page<ExpenditureResponse> searchByDate(LocalDate date, Pageable pageable);

    Page<ExpenditureResponse> searchBetweenTwoDate(LocalDate startDate, LocalDate endDate, Pageable pageable);

    Page<ExpenditureResponse> searchByMonth(Integer month, Pageable pageable);

    Page<ExpenditureResponse> findByCategory(String category, Pageable pageable);
}
