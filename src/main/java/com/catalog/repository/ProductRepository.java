package com.catalog.repository;

import com.catalog.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Interface de repositório para operações de persistência da entidade Product.
 *
 * Esta interface estende JpaRepository, que fornece automaticamente as operações
 * básicas de CRUD (Create, Read, Update, Delete) sem a necessidade de implementação
 * manual. O Spring Data JPA gera a implementação em tempo de execução.
 *
 * Métodos herdados do JpaRepository:
 *   - findAll(): retorna todos os produtos
 *   - findById(Long id): busca produto por ID
 *   - save(Product product): salva ou atualiza um produto
 *   - deleteById(Long id): exclui um produto por ID
 *   - existsById(Long id): verifica se um produto existe
 *   - count(): conta o total de produtos
 *
 * Métodos customizados com paginação:
 *   - findByBrandId(): busca produtos por marca com paginação
 *   - findByCategoryId(): busca produtos por categoria com paginação
 *
 * A anotação @Repository indica que esta interface é um repositório Spring
 * e será tratada como um bean gerenciado pelo container de injeção de dependências.
 */
@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    // JpaRepository<Product, Long>:
    //   - Product: tipo da entidade gerenciada
    //   - Long: tipo do atributo ID da entidade

    /**
     * Busca produtos por ID da marca com paginação.
     *
     * O Spring Data JPA gera automaticamente a query baseada no nome do método.
     * Equivale a: SELECT p FROM Product p WHERE p.brand.id = :brandId
     *
     * @param brandId  identificador da marca
     * @param pageable informações de paginação
     * @return página de produtos da marca
     */
    Page<Product> findByBrandId(Long brandId, Pageable pageable);

    /**
     * Busca produtos por ID da categoria com paginação.
     *
     * O Spring Data JPA gera automaticamente a query baseada no nome do método.
     * Equivale a: SELECT p FROM Product p WHERE p.category.id = :categoryId
     *
     * @param categoryId identificador da categoria
     * @param pageable   informações de paginação
     * @return página de produtos da categoria
     */
    Page<Product> findByCategoryId(Long categoryId, Pageable pageable);
}
