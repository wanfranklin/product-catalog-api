package com.catalog.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

/**
 * DTO (Data Transfer Object) para requisições de criação e atualização de produtos.
 *
 * Esta classe é utilizada para receber os dados enviados pelo cliente nas requisições
 * HTTP POST (criar) e PUT (atualizar). Ela valida os dados de entrada usando as
 * anotações de validação do Jakarta Bean Validation.
 *
 * Validações aplicadas:
 *   - name: não pode ser nulo ou vazio (NotBlank)
 *   - price: não pode ser nulo (NotNull) e deve ser maior que 0.01 (DecimalMin)
 *   - brandId: não pode ser nulo (NotNull)
 *   - categoryId: não pode ser nulo (NotNull)
 *   - description: campo opcional, pode ser nulo
 *
 * Observação: O campo SKU foi removido deste DTO pois agora é uma entidade separada.
 * Para criar SKUs, utilize o endpoint /api/skus.
 *
 * Exemplo de JSON esperado:
 * {
 *   "name": "Notebook Dell",
 *   "description": "Notebook Dell Inspiron 15",
 *   "price": 4999.99,
 *   "brandId": 1,
 *   "categoryId": 1
 * }
 */
public class ProductRequest {

    /** Nome do produto. Obrigatório - não pode ser vazio ou conter apenas espaços. */
    @NotBlank(message = "Nome do produto é obrigatório")
    private String name;

    /** Descrição detalhada do produto. Campo opcional. */
    private String description;

    /** Preço base do produto. Obrigatório - deve ser maior que zero. */
    @NotNull(message = "Preço do produto é obrigatório")
    @DecimalMin(value = "0.01", message = "Preço deve ser maior que zero")
    private BigDecimal price;

    /** ID da marca do produto. Obrigatório. */
    @NotNull(message = "Marca do produto é obrigatória")
    private Long brandId;

    /** ID da categoria do produto. Obrigatório. */
    @NotNull(message = "Categoria do produto é obrigatória")
    private Long categoryId;

    /**
     * Construtor padrão (necessário para desserialização JSON).
     */
    public ProductRequest() {
    }

    /**
     * Construtor com todos os campos.
     *
     * @param name        nome do produto
     * @param description descrição do produto
     * @param price       preço do produto
     * @param brandId     ID da marca do produto
     * @param categoryId  ID da categoria do produto
     */
    public ProductRequest(String name, String description, BigDecimal price, Long brandId, Long categoryId) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.brandId = brandId;
        this.categoryId = categoryId;
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

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Long getBrandId() {
        return brandId;
    }

    public void setBrandId(Long brandId) {
        this.brandId = brandId;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }
}
