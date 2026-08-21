package com.catalog.repository;

import com.catalog.entity.Product;
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
 * A anotação @Repository indica que esta interface é um repositório Spring
 * e será tratada como um bean gerenciado pelo container de injeção de dependências.
 */
@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    // JpaRepository<Product, Long>:
    //   - Product: tipo da entidade gerenciada
    //   - Long: tipo do atributo ID da entidade

    // Nenhum método customizado é necessário para esta aplicação,
    // pois os métodos padrão do JpaRepository já atendem todas as necessidades.
    // Exemplo de método customizado (se fosse necessário):
    // List<Product> findByNameContaining(String name);
}
