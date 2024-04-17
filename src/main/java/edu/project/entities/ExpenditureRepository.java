package edu.project.entities;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface ExpenditureRepository extends JpaRepository<ExpenditureEntity, Long> {

    /**
     * Find all expenditures by specific date
     * @param date {@link LocalDate}
     * @param pageable {@link Pageable} optional
     * @return Page < ExpenditureEntity >
     */
    Page<ExpenditureEntity> findAllByDate(LocalDate date, Pageable pageable);

    /**
     * Find all expenditures between two dates
     * @param startDate {@link LocalDate}
     * @param endDate {@link LocalDate}
     * @param pageable {@link Pageable}
     * @return Page < ExpenditureEntity >
     */
    Page<ExpenditureEntity> findAllByDateBetween(LocalDate startDate, LocalDate endDate, Pageable pageable);

    /**
     * Find all expenditures by specific month
     * @param month Integer
     * @param pageable {@link Pageable}
     * @return Page < ExpenditureEntity >
     */
    @Query(value = "SELECT e FROM ExpenditureEntity e WHERE Month(e.date) = :month")
    Page<ExpenditureEntity> findAllByMonth(Integer month, Pageable pageable);

    /**
     * Find all expenditures by category
     * @param id {@link Integer}
     * @param pageable {@link Pageable}
     * @return Page < ExpenditureEntity >
     */
    @Query(value = "SELECT e FROM ExpenditureEntity e WHERE e.category.id = :id")
    Page<ExpenditureEntity> findByCategory(Integer id, Pageable pageable);
}
