package com.catalog.repository;

import com.catalog.entity.Sku;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Interface de repositório para a entidade Sku.
 *
 * Esta interface estende JpaRepository, que fornece operações padrão de CRUD
 * (Create, Read, Update, Delete) para a entidade Sku.
 *
 * O Spring Data JPA gera automaticamente a implementação desta interface
 * em tempo de execução, eliminando a necessidade de escrever código boilerplate.
 *
 * Métodos disponíveis herdados do JpaRepository:
 *   - findAll(): Retorna todos os SKUs
 *   - findById(Long id): Busca um SKU por ID
 *   - save(Sku sku): Salva ou atualiza um SKU
 *   - deleteById(Long id): Exclui um SKU por ID
 *   - existsById(Long id): Verifica se um SKU existe
 *   - count(): Retorna o total de SKUs
 *
 * Métodos personalizados:
 *   - findByProductId(Long productId): Busca SKUs por produto
 */
@Repository
public interface SkuRepository extends JpaRepository<Sku, Long> {

    /**
     * Busca todos os SKUs de um produto específico.
     *
     * @param productId ID do produto
     * @return lista de SKUs do produto
     */
    List<Sku> findByProductId(Long productId);
}
