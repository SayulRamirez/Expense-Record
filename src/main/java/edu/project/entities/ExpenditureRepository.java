package edu.project.entities;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExpenditureRepository extends JpaRepository<ExpenditureEntity, Long> {
}
