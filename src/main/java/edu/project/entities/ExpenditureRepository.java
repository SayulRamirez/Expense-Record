package edu.project.entities;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface ExpenditureRepository extends JpaRepository<ExpenditureEntity, Long> {

    Page<ExpenditureEntity> findAllByDate(LocalDate date, Pageable pageable);

    Page<ExpenditureEntity> findAllByDateBetween(LocalDate startDate, LocalDate endDate, Pageable pageable);

    @Query(value = "SELECT e FROM ExpenditureEntity e WHERE Month(e.date) = :month")
    Page<ExpenditureEntity> findAllByMonth(Integer month, Pageable pageable);
}
