package edu.project.services;

import edu.project.domain.ExpenditureRequest;
import edu.project.domain.ExpenditureResponse;
import edu.project.entities.CategoryEntity;
import edu.project.entities.CategoryRepository;
import edu.project.entities.ExpenditureEntity;
import edu.project.entities.ExpenditureRepository;
import edu.project.exceptions.CategoryNotFound;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ExpenditureServiceImpl implements ExpenditureService{

    private final CategoryRepository categoryRepository;
    private final ExpenditureRepository expenditureRepository;

    @Override
    public ExpenditureResponse register(ExpenditureRequest request) {

        CategoryEntity category = categoryRepository.findCategoryEntityByCategory(request.category()).orElseThrow(() -> new CategoryNotFound("Category not found"));

        ExpenditureEntity expenditure = ExpenditureEntity.builder()
                .title(request.title())
                .description(request.description())
                .amount(request.amount())
                .date(request.date())
                .category(category)
                .build();

        expenditureRepository.save(expenditure);

        return new ExpenditureResponse(
                expenditure.getTitle(),
                expenditure.getDescription(),
                expenditure.getAmount(),
                expenditure.getDate(),
                expenditure.getCategory().getCategory());
    }
}
