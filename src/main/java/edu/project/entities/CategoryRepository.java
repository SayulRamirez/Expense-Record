package edu.project.entities;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<CategoryEntity, Integer> {

    /**
     * Find category by name category
     * @param category {@link String}
     * @return Optional <CategoryEntity>
     */
    Optional<CategoryEntity> findCategoryEntityByCategory(String category);
}
