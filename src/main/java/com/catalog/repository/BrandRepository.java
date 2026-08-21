package com.catalog.repository;

import com.catalog.entity.Brand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Interface de repositório para operações de persistência da entidade Brand.
 *
 * Estende JpaRepository para fornecer operações automáticas de CRUD.
 */
@Repository
public interface BrandRepository extends JpaRepository<Brand, Long> {
}
