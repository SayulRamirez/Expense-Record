package edu.project.services;

import edu.project.domain.ExpenditureRequest;
import edu.project.domain.ExpenditureResponse;
import edu.project.domain.ExpenditureUpdate;
import edu.project.entities.CategoryEntity;
import edu.project.entities.CategoryRepository;
import edu.project.entities.ExpenditureEntity;
import edu.project.entities.ExpenditureRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@RequiredArgsConstructor
@Service
public class ExpenditureServiceImpl implements ExpenditureService{

    private final CategoryRepository categoryRepository;
    private final ExpenditureRepository expenditureRepository;

    @Override
    public ExpenditureResponse register(ExpenditureRequest request, LocalDate date) {

        CategoryEntity category = categoryRepository.findCategoryEntityByCategory(request.category()).orElseThrow(() -> new EntityNotFoundException("Category not found whit: " + request.category()));

        ExpenditureEntity expenditure = ExpenditureEntity.builder()
                .title(request.title())
                .description(request.description())
                .amount(request.amount())
                .date(date)
                .category(category)
                .build();

        expenditureRepository.save(expenditure);

        return new ExpenditureResponse(
                expenditure.getId(),
                expenditure.getTitle(),
                expenditure.getDescription(),
                expenditure.getAmount(),
                expenditure.getDate(),
                expenditure.getCategory().getCategory());
    }

    @Override
    public ExpenditureResponse edit(ExpenditureUpdate request, LocalDate date) {

        ExpenditureEntity expenditure = expenditureRepository.findById(request.id()).orElseThrow(() -> new EntityNotFoundException("The expenditure not found whit id: " + request.id()));

        expenditure.setTitle(request.title());
        expenditure.setDescription(request.description());
        expenditure.setAmount(request.amount());
        expenditure.setDate(date);

        if (!expenditure.getCategory().getCategory().equals(request.category())) {

            CategoryEntity category = categoryRepository.findCategoryEntityByCategory(request.category()).orElseThrow(() -> new EntityNotFoundException("The category not found whit: " + request.category()));
            expenditure.setCategory(category);
        }

        expenditureRepository.save(expenditure);

        return new ExpenditureResponse(
                expenditure.getId(),
                expenditure.getTitle(),
                expenditure.getDescription(),
                expenditure.getAmount(),
                expenditure.getDate(),
                expenditure.getCategory().getCategory());
    }

    @Override
    public Page<ExpenditureResponse> findAll(Pageable pageable) {

        Page<ExpenditureEntity> expenditures = expenditureRepository.findAll(pageable);

        return expenditures.map(expenditure -> new ExpenditureResponse(
                expenditure.getId(),
                expenditure.getTitle(),
                expenditure.getDescription(),
                expenditure.getAmount(),
                expenditure.getDate(),
                expenditure.getCategory().getCategory())
        );
    }

    @Override
    public Page<ExpenditureResponse> searchByDate(LocalDate date, Pageable pageable) {

        Page<ExpenditureEntity> expenditures = expenditureRepository.findAllByDate(date, pageable);

        return expenditures.map(expenditure -> new ExpenditureResponse(
                expenditure.getId(),
                expenditure.getTitle(),
                expenditure.getDescription(),
                expenditure.getAmount(),
                expenditure.getDate(),
                expenditure.getCategory().getCategory())
        );
    }

    @Override
    public Page<ExpenditureResponse> searchBetweenTwoDate(LocalDate startDate, LocalDate endDate, Pageable pageable) {

        Page<ExpenditureEntity> expenditures = expenditureRepository.findAllByDateBetween(startDate, endDate, pageable);

        return expenditures.map(expenditure -> new ExpenditureResponse(
                expenditure.getId(),
                expenditure.getTitle(),
                expenditure.getDescription(),
                expenditure.getAmount(),
                expenditure.getDate(),
                expenditure.getCategory().getCategory())
        );
    }

    @Override
    public Page<ExpenditureResponse> searchByMonth(Integer month, Pageable pageable) {

        Page<ExpenditureEntity> expenditures = expenditureRepository.findAllByMonth(month, pageable);

        return expenditures.map(expenditure -> new ExpenditureResponse(
                expenditure.getId(),
                expenditure.getTitle(),
                expenditure.getDescription(),
                expenditure.getAmount(),
                expenditure.getDate(),
                expenditure.getCategory().getCategory())
        );
    }

    @Override
    public Page<ExpenditureResponse> findByCategory(String category, Pageable pageable) {

        Integer id = categoryRepository.findCategoryEntityByCategory(category).orElseThrow(() -> new EntityNotFoundException("Category not found whit: " + category)).getId();

        Page<ExpenditureEntity> expenditures = expenditureRepository.findByCategory(id, pageable);

        return expenditures.map(expenditure -> new ExpenditureResponse(
                expenditure.getId(),
                expenditure.getTitle(),
                expenditure.getDescription(),
                expenditure.getAmount(),
                expenditure.getDate(),
                expenditure.getCategory().getCategory())
        );
    }

    @Override
    public void delete(Long id) {
        expenditureRepository.deleteById(id);
    }
}
