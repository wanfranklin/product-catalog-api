package com.catalog.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Entidade que representa um produto no catálogo.
 *
 * Esta classe é mapeada para a tabela "products" no banco de dados MySQL.
 * Utiliza JPA (Java Persistence API) para persistência via Hibernate.
 *
 * Relacionamentos:
 *   - Brand (Marca): Muitos produtos para uma marca (N:1)
 *   - Category (Categoria): Muitos produtos para uma categoria (N:1)
 *   - Sku (SKU): Um produto pode ter vários SKUs (1:N)
 *
 * Atributos:
 *   - id: Identificador único auto-incremento gerado pelo banco
 *   - name: Nome do produto (obrigatório)
 *   - description: Descrição detalhada do produto (opcional)
 *   - price: Preço base do produto com precisão decimal (obrigatório, deve ser > 0)
 *   - brand: Marca do produto (obrigatório)
 *   - category: Categoria do produto (obrigatório)
 *   - skus: Lista de SKUs do produto (variações com código, preço e estoque próprios)
 */
@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    private String description;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal price;

    /**
     * Marca do produto (relacionamento Muitos para Um).
     * Cada produto pertence a uma marca.
     * fetch = LAZY: a marca é carregada apenas quando acessada (otimização de performance).
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "brand_id", nullable = false)
    private Brand brand;

    /**
     * Categoria do produto (relacionamento Muitos para Um).
     * Cada produto pertence a uma categoria.
     * fetch = LAZY: a categoria é carregada apenas quando acessada.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    /**
     * Lista de SKUs do produto (relacionamento Um para Muitos).
     * Um produto pode ter vários SKUs (variações).
     * cascade = ALL: ao excluir o produto, todos os seus SKUs são excluídos.
     * fetch = LAZY: os SKUs são carregados apenas quando acessados.
     */
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Sku> skus = new ArrayList<>();

    /**
     * Construtor padrão (obrigatório para JPA).
     */
    public Product() {
    }

    /**
     * Construtor com todos os atributos (exceto id e skus).
     *
     * @param id          identificador do produto
     * @param name        nome do produto
     * @param description descrição do produto
     * @param price       preço do produto
     * @param brand       marca do produto
     * @param category    categoria do produto
     */
    public Product(Long id, String name, String description, BigDecimal price, Brand brand, Category category) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.brand = brand;
        this.category = category;
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

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public List<Sku> getSkus() {
        return skus;
    }

    public void setSkus(List<Sku> skus) {
        this.skus = skus;
    }
}
