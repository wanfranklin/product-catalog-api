package com.catalog.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

/**
 * DTO (Data Transfer Object) para requisições de criação e atualização de SKUs.
 *
 * Esta classe é utilizada para receber os dados enviados pelo cliente nas requisições
 * HTTP POST (criar) e PUT (atualizar). Ela valida os dados de entrada usando as
 * anotações de validação do Jakarta Bean Validation.
 *
 * Validações aplicadas:
 *   - code: não pode ser nulo ou vazio (NotBlank)
 *   - price: não pode ser nulo (NotNull) e deve ser maior que 0.01 (DecimalMin)
 *   - stock: não pode ser nulo (NotNull) e deve ser maior ou igual a 0 (Min)
 *   - productId: não pode ser nulo (NotNull)
 *
 * Exemplo de JSON esperado:
 * {
 *   "code": "NB-DELL-001-PRETO",
 *   "price": 4999.99,
 *   "stock": 100,
 *   "productId": 1
 * }
 */
public class SkuRequest {

    /** Código SKU (Stock Keeping Unit). Obrigatório e único. */
    @NotBlank(message = "Código SKU é obrigatório")
    private String code;

    /** Preço específico deste SKU. Obrigatório - deve ser maior que zero. */
    @NotNull(message = "Preço do SKU é obrigatório")
    @DecimalMin(value = "0.01", message = "Preço deve ser maior que zero")
    private BigDecimal price;

    /** Quantidade em estoque deste SKU. Obrigatório - deve ser maior ou igual a zero. */
    @NotNull(message = "Estoque do SKU é obrigatório")
    @Min(value = 0, message = "Estoque não pode ser negativo")
    private Integer stock;

    /** ID do produto ao qual este SKU pertence. Obrigatório. */
    @NotNull(message = "Produto do SKU é obrigatório")
    private Long productId;

    /**
     * Construtor padrão (necessário para desserialização JSON).
     */
    public SkuRequest() {
    }

    /**
     * Construtor com todos os campos.
     *
     * @param code      código SKU
     * @param price     preço do SKU
     * @param stock     quantidade em estoque
     * @param productId ID do produto associado
     */
    public SkuRequest(String code, BigDecimal price, Integer stock, Long productId) {
        this.code = code;
        this.price = price;
        this.stock = stock;
        this.productId = productId;
    }

    // ==================== GETTERS E SETTERS ====================

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }
}
