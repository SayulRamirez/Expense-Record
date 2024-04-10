package edu.project.controller;

import edu.project.domain.ExpenditureRequest;
import edu.project.domain.ExpenditureResponse;
import edu.project.domain.ExpenditureUpdate;
import edu.project.exceptions.DateValidationException;
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
}
