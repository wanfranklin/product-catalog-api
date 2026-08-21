package com.catalog.dto;

import com.catalog.entity.Product;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

/**
 * DTO (Data Transfer Object) para respostas da API referentes a produtos.
 *
 * O campo SKU foi removido - agora é uma entidade separada (1 produto N SKUs).
 * Para buscar SKUs de um produto, utilize GET /api/skus/product/{productId}.
 *
 * Exemplo de JSON retornado:
 * {
 *   "id": 1,
 *   "name": "Notebook Dell",
 *   "description": "Notebook Dell Inspiron 15",
 *   "price": 4999.99,
 *   "brand": { "id": 1, "name": "Dell", "description": "..." },
 *   "category": { "id": 1, "name": "Notebooks", "description": "..." }
 * }
 */
public class ProductResponse {

    private Long id;

    private String name;

    private String description;

    private BigDecimal price;

    private BrandResponse brand;

    private CategoryResponse category;

    private List<SkuResponse> skus;

    public ProductResponse() {
    }

    public ProductResponse(Long id, String name, String description, BigDecimal price, BrandResponse brand, CategoryResponse category, List<SkuResponse> skus) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.brand = brand;
        this.category = category;
        this.skus = skus;
    }

    /**
     * Converte uma entidade Product em um ProductResponse (com SKUs).
     *
     * @param product entidade Product vinda do banco de dados
     * @return novo ProductResponse com os dados da entidade
     */
    public static ProductResponse fromEntity(Product product) {
        BrandResponse brandResponse = null;
        if (product.getBrand() != null) {
            brandResponse = BrandResponse.fromEntity(product.getBrand());
        }

        CategoryResponse categoryResponse = null;
        if (product.getCategory() != null) {
            categoryResponse = CategoryResponse.fromEntity(product.getCategory());
        }

        List<SkuResponse> skuResponses = null;
        if (product.getSkus() != null) {
            skuResponses = product.getSkus().stream()
                    .map(SkuResponse::fromEntity)
                    .collect(Collectors.toList());
        }

        return new ProductResponse(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                brandResponse,
                categoryResponse,
                skuResponses
        );
    }

    /**
     * Converte uma entidade Product em um ProductResponse (sem SKUs).
     * Usado para evitar referência circular quando chamado de SkuResponse.
     *
     * @param product entidade Product vinda do banco de dados
     * @return novo ProductResponse sem a lista de SKUs
     */
    public static ProductResponse fromEntityWithoutSkus(Product product) {
        BrandResponse brandResponse = null;
        if (product.getBrand() != null) {
            brandResponse = BrandResponse.fromEntity(product.getBrand());
        }

        CategoryResponse categoryResponse = null;
        if (product.getCategory() != null) {
            categoryResponse = CategoryResponse.fromEntity(product.getCategory());
        }

        return new ProductResponse(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                brandResponse,
                categoryResponse,
                null
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

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BrandResponse getBrand() {
        return brand;
    }

    public void setBrand(BrandResponse brand) {
        this.brand = brand;
    }

    public CategoryResponse getCategory() {
        return category;
    }

    public void setCategory(CategoryResponse category) {
        this.category = category;
    }

    public List<SkuResponse> getSkus() {
        return skus;
    }

    public void setSkus(List<SkuResponse> skus) {
        this.skus = skus;
    }
}
