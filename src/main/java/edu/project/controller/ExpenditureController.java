package edu.project.controller;

import edu.project.domain.ExpenditureRequest;
import edu.project.domain.ExpenditureResponse;
import edu.project.exceptions.AmountInvalid;
import edu.project.services.ExpenditureService;
import edu.project.services.ExpenditureServiceImpl;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/expenditure")
public class ExpenditureController {

    private final ExpenditureService expenditureService;

    public ExpenditureController(ExpenditureServiceImpl expenditureService) {
        this.expenditureService = expenditureService;
    }

    @PostMapping
    public ResponseEntity<ExpenditureResponse> registerExpenditure(@Valid @RequestBody ExpenditureRequest request) {

        if (request.amount() < 1) throw new AmountInvalid("The minimum amount must be 1");

        ExpenditureResponse response = expenditureService.register(request);

        return ResponseEntity.ok(response);
    }
}
