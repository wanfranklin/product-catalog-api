-- ============================================================
-- Script SQL - Product Catalog API
-- Versão: 2.0.0
-- Data: 21/08/2026
-- Banco de Dados: MySQL 8.0+
-- ============================================================

-- Cria o banco de dados se não existir
CREATE DATABASE IF NOT EXISTS product_catalog
    CHARACTER SET utf8mb4
    COLLATE utf8mb4_unicode_ci;

-- Seleciona o banco de dados
USE product_catalog;

-- ============================================================
-- Tabela: brands
-- Descrição: Armazena as marcas de produtos
-- ============================================================
CREATE TABLE IF NOT EXISTS brands (
    id          BIGINT       NOT NULL AUTO_INCREMENT,
    name        VARCHAR(255) NOT NULL,
    description VARCHAR(255),
    PRIMARY KEY (id),
    UNIQUE KEY uk_brands_name (name)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ============================================================
-- Tabela: categories
-- Descrição: Armazena as categorias de produtos
-- ============================================================
CREATE TABLE IF NOT EXISTS categories (
    id          BIGINT       NOT NULL AUTO_INCREMENT,
    name        VARCHAR(255) NOT NULL,
    description VARCHAR(255),
    PRIMARY KEY (id),
    UNIQUE KEY uk_categories_name (name)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ============================================================
-- Tabela: products
-- Descrição: Armazena os produtos do catálogo
-- Relacionamentos: brands (N:1), categories (N:1)
-- ============================================================
CREATE TABLE IF NOT EXISTS products (
    id          BIGINT       NOT NULL AUTO_INCREMENT,
    name        VARCHAR(255) NOT NULL,
    description VARCHAR(255),
    price       DECIMAL(10,2) NOT NULL,
    brand_id    BIGINT       NOT NULL,
    category_id BIGINT       NOT NULL,
    PRIMARY KEY (id),
    KEY fk_products_brand (brand_id),
    KEY fk_products_category (category_id),
    CONSTRAINT fk_products_brand FOREIGN KEY (brand_id) REFERENCES brands (id),
    CONSTRAINT fk_products_category FOREIGN KEY (category_id) REFERENCES categories (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ============================================================
-- Tabela: skus
-- Descrição: Armazena os SKUs (variações) dos produtos
-- Relacionamento: products (N:1)
-- Um produto pode ter vários SKUs (ex: camiseta P/M/G)
-- ============================================================
CREATE TABLE IF NOT EXISTS skus (
    id         BIGINT        NOT NULL AUTO_INCREMENT,
    code       VARCHAR(255)  NOT NULL,
    price      DECIMAL(10,2) NOT NULL,
    stock      INT           NOT NULL DEFAULT 0,
    product_id BIGINT        NOT NULL,
    PRIMARY KEY (id),
    UNIQUE KEY uk_skus_code (code),
    KEY fk_skus_product (product_id),
    CONSTRAINT fk_skus_product FOREIGN KEY (product_id) REFERENCES products (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ============================================================
-- Fim do script
-- ============================================================
