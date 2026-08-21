package com.catalog.repository;

import com.catalog.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Interface de repositório para operações de persistência da entidade Category.
 *
 * Estende JpaRepository para fornecer operações automáticas de CRUD.
 */
@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
}
