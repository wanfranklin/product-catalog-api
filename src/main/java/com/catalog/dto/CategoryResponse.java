package com.catalog.dto;

import com.catalog.entity.Category;

/**
 * DTO (Data Transfer Object) para respostas da API referentes a categorias.
 *
 * O método estático fromEntity() converte uma entidade Category em um CategoryResponse.
 */
public class CategoryResponse {

    /** Identificador único da categoria. */
    private Long id;

    /** Nome da categoria. */
    private String name;

    /** Descrição detalhada da categoria. */
    private String description;

    /**
     * Construtor padrão (necessário para serialização JSON).
     */
    public CategoryResponse() {
    }

    /**
     * Construtor com todos os campos.
     *
     * @param id          identificador da categoria
     * @param name        nome da categoria
     * @param description descrição da categoria
     */
    public CategoryResponse(Long id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    /**
     * Converte uma entidade Category em um CategoryResponse.
     *
     * @param category entidade Category vinda do banco de dados
     * @return novo CategoryResponse com os dados da entidade
     */
    public static CategoryResponse fromEntity(Category category) {
        return new CategoryResponse(
                category.getId(),
                category.getName(),
                category.getDescription()
        );
    }

    // ==================== GETTERS E SETTERS ====================

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
