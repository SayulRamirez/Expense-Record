package edu.project.services;

import edu.project.domain.ExpenditureRequest;
import edu.project.domain.ExpenditureResponse;
import edu.project.domain.ExpenditureUpdate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;

public interface ExpenditureService {

    /**
     * Register new expenditure.
     * @param request {@link ExpenditureRequest}
     * @param date {@link LocalDate}
     * @return ExpenditureResponse
     */
    ExpenditureResponse register(ExpenditureRequest request, LocalDate date);

    /**
     * Edit o update a expenditure.
     * @param request {@link ExpenditureUpdate}
     * @param date {@link LocalDate}
     * @return ExpenditureResponse
     */
    ExpenditureResponse edit(ExpenditureUpdate request, LocalDate date);

    /**
     * Find all expenses without any filter.
     * The {@link Pageable} is optional, although it is recommended in order to be able to paginate the results
     * @param pageable {@link Pageable}
     * @return Page <{@link ExpenditureResponse}> if a pageable was entered, it will return a Page with the chosen pagination, otherwise it will return a Page with the default pagination
     */
    Page<ExpenditureResponse> findAll(Pageable pageable);

    /**
     * Find all expenses filtered by a specific date
     * @param date {@link LocalDate} date to be filtered
     * @param pageable The {@link Pageable} is optional, although it is recommended in order to be able to paginate the results
     * @return Page <{@link ExpenditureResponse}> if a pageable was entered, it will return a Page with the chosen pagination, otherwise it will return a Page with the default pagination
     */
    Page<ExpenditureResponse> searchByDate(LocalDate date, Pageable pageable);

    /**
     * Find all expenses filtered between two date
     * @param startDate {@link LocalDate} start date
     * @param endDate {@link LocalDate} end date
     * @param pageable The {@link Pageable} is optional, although it is recommended in order to be able to paginate the results
     * @return Page <{@link ExpenditureResponse}> if a pageable was entered, it will return a Page with the chosen pagination, otherwise it will return a Page with the default pagination
     */
    Page<ExpenditureResponse> searchBetweenTwoDate(LocalDate startDate, LocalDate endDate, Pageable pageable);

    /**
     * Find all expenses filtered by a specific month
     * @param month Month
     * @param pageable The {@link Pageable} is optional, although it is recommended in order to be able to paginate the results
     * @return Page <{@link ExpenditureResponse}> if a pageable was entered, it will return a Page with the chosen pagination, otherwise it will return a Page with the default pagination
     */
    Page<ExpenditureResponse> searchByMonth(Integer month, Pageable pageable);

    /**
     * Find all expenses filtered by a specific category
     * @param category {@link String}
     * @param pageable The {@link Pageable} is optional, although it is recommended in order to be able to paginate the results
     * @return Page <{@link ExpenditureResponse}> if a pageable was entered, it will return a Page with the chosen pagination, otherwise it will return a Page with the default pagination
     */
    Page<ExpenditureResponse> findByCategory(String category, Pageable pageable);

    /**
     * Delete expense by id
     * @param id {@link Long}
     */
    void delete(Long id);
}
