package edu.project.services;

import edu.project.domain.ExpenditureRequest;
import edu.project.domain.ExpenditureResponse;

public interface ExpenditureService {
    ExpenditureResponse register(ExpenditureRequest request);
}
