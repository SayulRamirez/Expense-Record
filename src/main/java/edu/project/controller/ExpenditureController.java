package edu.project.controller;

import edu.project.domain.ExpenditureRequest;
import edu.project.domain.ExpenditureResponse;
import edu.project.domain.ExpenditureUpdate;
import edu.project.services.ExpenditureService;
import edu.project.services.ExpenditureServiceImpl;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

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

        return ResponseEntity.ok(response);
    }

    @Transactional
    @PutMapping
    public ResponseEntity<ExpenditureResponse> editExpenditure(@Valid @RequestBody ExpenditureUpdate request) {

        LocalDate date = LocalDate.parse(request.date());

        ExpenditureResponse response = expenditureService.edit(request, date);

        return ResponseEntity.ok(response);
    }
}
