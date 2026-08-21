package com.catalog.dto;

import jakarta.validation.constraints.NotBlank;

/**
 * DTO (Data Transfer Object) para requisições de criação e atualização de marcas.
 *
 * Validações aplicadas:
 *   - name: não pode ser nulo ou vazio (NotBlank)
 *   - description: campo opcional, pode ser nulo
 */
public class BrandRequest {

    /** Nome da marca. Obrigatório - não pode ser vazio. */
    @NotBlank(message = "Nome da marca é obrigatório")
    private String name;

    /** Descrição detalhada da marca. Campo opcional. */
    private String description;

    /**
     * Construtor padrão (necessário para desserialização JSON).
     */
    public BrandRequest() {
    }

    /**
     * Construtor com todos os campos.
     *
     * @param name        nome da marca
     * @param description descrição da marca
     */
    public BrandRequest(String name, String description) {
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
