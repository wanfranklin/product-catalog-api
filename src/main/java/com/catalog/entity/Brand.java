package com.catalog.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * Entidade que representa uma marca de produto no catálogo.
 *
 * Esta classe é mapeada para a tabela "brands" no banco de dados MySQL.
 * Uma marca pode ter vários produtos associados (relacionamento 1:N com Product).
 *
 * Atributos:
 *   - id: Identificador único auto-incremento gerado pelo banco
 *   - name: Nome da marca (obrigatório, único)
 *   - description: Descrição da marca (opcional)
 */
@Entity
@Table(name = "brands")
public class Brand {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    private String description;

    /**
     * Construtor padrão (obrigatório para JPA).
     */
    public Brand() {
    }

    /**
     * Construtor com todos os atributos (exceto id).
     *
     * @param id          identificador da marca
     * @param name        nome da marca
     * @param description descrição da marca
     */
    public Brand(Long id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
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
