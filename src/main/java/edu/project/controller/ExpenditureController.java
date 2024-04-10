package edu.project.controller;

import edu.project.domain.ExpenditureRequest;
import edu.project.domain.ExpenditureResponse;
import edu.project.domain.ExpenditureUpdate;
import edu.project.exceptions.DateValidationException;
import edu.project.exceptions.MonthInvalidException;
import edu.project.services.ExpenditureService;
import edu.project.services.ExpenditureServiceImpl;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.time.LocalDate;

@RestController
@RequestMapping("/api/v1/expenditure")
public class ExpenditureController {

    private final ExpenditureService expenditureService;

    public ExpenditureController(ExpenditureServiceImpl expenditureService) {
        this.expenditureService = expenditureService;
    }

    @Transactional
    @PostMapping
    public ResponseEntity<ExpenditureResponse> registerExpenditure(@Valid @RequestBody ExpenditureRequest request) {

        LocalDate date = LocalDate.parse(request.date());

        ExpenditureResponse response = expenditureService.register(request, date);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(response.id()).toUri();

        return ResponseEntity.created(location).body(response);
    }

    @Transactional
    @PutMapping
    public ResponseEntity<ExpenditureResponse> editExpenditure(@Valid @RequestBody ExpenditureUpdate request) {

        LocalDate date = LocalDate.parse(request.date());

        ExpenditureResponse response = expenditureService.edit(request, date);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/all")
    public ResponseEntity<Page<ExpenditureResponse>> getAllExpenditure(Pageable pageable) {

        return ResponseEntity.ok(expenditureService.findAll(pageable));
    }

    @GetMapping("/search/date={date}")
    public ResponseEntity<Page<ExpenditureResponse>> searchExpenditureByDate(@PathVariable LocalDate date, Pageable pageable) {

        return ResponseEntity.ok(expenditureService.searchByDate(date, pageable));
    }

    @GetMapping("/search/startDate={startDate}&endDate={endDate}")
    public ResponseEntity<Page<ExpenditureResponse>> searchExpenditureBetweenTwoDate(
            @PathVariable LocalDate startDate, @PathVariable LocalDate endDate, Pageable pageable) {

        if (endDate.isBefore(startDate)) throw new DateValidationException("The end date: " + endDate + ", must be after the start date: " + startDate);

        return ResponseEntity.ok(expenditureService.searchBetweenTwoDate(startDate, endDate, pageable));
    }

    @GetMapping("/search/month={month}")
    public ResponseEntity<Page<ExpenditureResponse>> searchExpenditureByMonth(@PathVariable Integer month, Pageable pageable) {

        if (month == null || month < 1 || month > 12) throw new MonthInvalidException("The month must be between 1 and 12");

        return ResponseEntity.ok(expenditureService.searchByMonth(month, pageable));
    }

    @GetMapping("/search/category={category}")
    public ResponseEntity<Page<ExpenditureResponse>> searchExpenditureByCategory(@PathVariable String category, Pageable pageable) {

        return ResponseEntity.ok(expenditureService.findByCategory(category, pageable));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {

        expenditureService.delete(id);

        return ResponseEntity.notFound().build();
    }
}
