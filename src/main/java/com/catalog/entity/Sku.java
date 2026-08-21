package com.catalog.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import java.math.BigDecimal;

/**
 * Entidade que representa um SKU (Stock Keeping Unit) de um produto.
 *
 * Um produto pode ter vários SKUs (ex: camiseta P/M/G = 3 SKUs).
 * Cada SKU representa uma variação específica do produto com seu próprio
 * código, preço e quantidade em estoque.
 *
 * Relacionamentos:
 *   - Product (Produto): Muitos SKUs para um produto (N:1)
 *
 * Atributos:
 *   - id: Identificador único auto-incremento gerado pelo banco
 *   - code: Código SKU único (obrigatório)
 *   - price: Preço específico deste SKU (pode diferir do preço base do produto)
 *   - stock: Quantidade em estoque deste SKU
 *   - product: Produto ao qual este SKU pertence
 */
@Entity
@Table(name = "skus")
public class Sku {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String code;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal price;

    @Column(nullable = false)
    private Integer stock;

    /**
     * Produto ao qual este SKU pertence (relacionamento Muitos para Um).
     * Cada SKU está associado a um produto.
     * fetch = LAZY: o produto é carregado apenas quando acessado.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    /**
     * Construtor padrão (obrigatório para JPA).
     */
    public Sku() {
    }

    /**
     * Construtor com todos os atributos (exceto id).
     *
     * @param id      identificador do SKU
     * @param code    código SKU
     * @param price   preço do SKU
     * @param stock   quantidade em estoque
     * @param product produto associado
     */
    public Sku(Long id, String code, BigDecimal price, Integer stock, Product product) {
        this.id = id;
        this.code = code;
        this.price = price;
        this.stock = stock;
        this.product = product;
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

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
