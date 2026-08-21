package com.catalog.dto;

import jakarta.validation.constraints.NotBlank;

/**
 * DTO (Data Transfer Object) para requisições de criação e atualização de categorias.
 *
 * Validações aplicadas:
 *   - name: não pode ser nulo ou vazio (NotBlank)
 *   - description: campo opcional, pode ser nulo
 */
public class CategoryRequest {

    /** Nome da categoria. Obrigatório - não pode ser vazio. */
    @NotBlank(message = "Nome da categoria é obrigatório")
    private String name;

    /** Descrição detalhada da categoria. Campo opcional. */
    private String description;

    /**
     * Construtor padrão (necessário para desserialização JSON).
     */
    public CategoryRequest() {
    }

    /**
     * Construtor com todos os campos.
     *
     * @param name        nome da categoria
     * @param description descrição da categoria
     */
    public CategoryRequest(String name, String description) {
        this.name = name;
        this.description = description;
    }

    // ==================== GETTERS E SETTERS ====================

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
