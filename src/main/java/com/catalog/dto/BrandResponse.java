package com.catalog.dto;

import com.catalog.entity.Brand;

/**
 * DTO (Data Transfer Object) para respostas da API referentes a marcas.
 *
 * O método estático fromEntity() converte uma entidade Brand em um BrandResponse.
 */
public class BrandResponse {

    /** Identificador único da marca. */
    private Long id;

    /** Nome da marca. */
    private String name;

    /** Descrição detalhada da marca. */
    private String description;

    /**
     * Construtor padrão (necessário para serialização JSON).
     */
    public BrandResponse() {
    }

    /**
     * Construtor com todos os campos.
     *
     * @param id          identificador da marca
     * @param name        nome da marca
     * @param description descrição da marca
     */
    public BrandResponse(Long id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    /**
     * Converte uma entidade Brand em um BrandResponse.
     *
     * @param brand entidade Brand vinda do banco de dados
     * @return novo BrandResponse com os dados da entidade
     */
    public static BrandResponse fromEntity(Brand brand) {
        return new BrandResponse(
                brand.getId(),
                brand.getName(),
                brand.getDescription()
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
