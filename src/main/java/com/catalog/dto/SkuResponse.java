package com.catalog.dto;

import com.catalog.entity.Sku;

import java.math.BigDecimal;

/**
 * DTO (Data Transfer Object) para respostas da API referentes a SKUs.
 *
 * Esta classe é utilizada para retornar os dados dos SKUs nas respostas HTTP.
 * Ela encapsula os dados relevantes que serão enviados ao cliente,
 * ocultando detalhes internos da entidade que não precisam ser expostos.
 *
 * O método estático fromEntity() converte uma entidade Sku em um SkuResponse,
 * facilitando a transformação de dados do banco para o formato da API.
 *
 * Exemplo de JSON retornado:
 * {
 *   "id": 1,
 *   "code": "NB-DELL-001-PRETO",
 *   "price": 4999.99,
 *   "stock": 100,
 *   "product": { "id": 1, "name": "Notebook Dell", ... }
 * }
 */
public class SkuResponse {

    /** Identificador único do SKU (gerado pelo banco de dados). */
    private Long id;

    /** Código SKU. */
    private String code;

    /** Preço específico deste SKU. */
    private BigDecimal price;

    /** Quantidade em estoque. */
    private Integer stock;

    /** Produto ao qual este SKU pertence (objeto aninhado). */
    private ProductResponse product;

    /**
     * Construtor padrão (necessário para serialização JSON).
     */
    public SkuResponse() {
    }

    /**
     * Construtor com todos os campos.
     *
     * @param id      identificador do SKU
     * @param code    código SKU
     * @param price   preço do SKU
     * @param stock   quantidade em estoque
     * @param product produto associado
     */
    public SkuResponse(Long id, String code, BigDecimal price, Integer stock, ProductResponse product) {
        this.id = id;
        this.code = code;
        this.price = price;
        this.stock = stock;
        this.product = product;
    }

    /**
     * Converte uma entidade Sku em um SkuResponse (com dados do produto).
     *
     * @param sku entidade Sku vinda do banco de dados
     * @return novo SkuResponse com os dados da entidade
     */
    public static SkuResponse fromEntity(Sku sku) {
        ProductResponse productResponse = null;
        if (sku.getProduct() != null) {
            // Usa o construtor sem skus para evitar referência circular
            productResponse = ProductResponse.fromEntityWithoutSkus(sku.getProduct());
        }

        return new SkuResponse(
                sku.getId(),
                sku.getCode(),
                sku.getPrice(),
                sku.getStock(),
                productResponse
        );
    }

    // ==================== GETTERS E SETTERS ====================

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public ProductResponse getProduct() {
        return product;
    }

    public void setProduct(ProductResponse product) {
        this.product = product;
    }
}
