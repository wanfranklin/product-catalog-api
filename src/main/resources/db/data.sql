-- ============================================================
-- Script de Dados de Exemplo - Product Catalog API
-- Versão: 2.0.0
-- Data: 21/08/2026
-- Banco de Dados: MySQL 8.0+
-- ============================================================
-- ATENÇÃO: Execute este script apenas após executar o schema.sql
-- ou após a primeira execução da aplicação (que cria as tabelas automaticamente)
-- ============================================================

USE product_catalog;

-- ============================================================
-- Inserir marcas de exemplo
-- ============================================================
INSERT INTO brands (name, description) VALUES
('Dell', 'Tecnologia e soluções de informática'),
('Logitech', 'Periféricos de computador e acessórios'),
('Keychron', 'Teclados mecânicos de alta qualidade'),
('LG', 'Eletrônicos e soluções de display'),
('Samsung', 'Eletrônicos e tecnologia');

-- ============================================================
-- Inserir categorias de exemplo
-- ============================================================
INSERT INTO categories (name, description) VALUES
('Notebooks', 'Computadores portáteis e laptops'),
('Periféricos', 'Mouses, teclados e acessórios'),
('Monitores', 'Monitores e telas de exibição'),
('Webcams', 'Câmeras para videoconferência'),
('Acessórios', 'Acessórios diversos para computadores');

-- ============================================================
-- Inserir produtos de exemplo (sem SKU - agora é entidade separada)
-- ============================================================
INSERT INTO products (name, description, price, brand_id, category_id) VALUES
('Notebook Dell Inspiron 15', 'Notebook Dell Inspiron 15, Intel Core i7, 16GB RAM, 512GB SSD', 4999.99, 1, 1),
('Mouse Logitech MX Master 3', 'Mouse wireless ergonômico para produtividade, Bluetooth/USB', 399.90, 2, 2),
('Teclado Mecânico Keychron K2', 'Teclado mecânico wireless, switches Blue, layout ABNT2', 549.00, 3, 2),
('Monitor LG UltraWide 34"', 'Monitor ultrawide 34 polegadas, 3440x1440, IPS, USB-C', 2899.00, 4, 3),
('Webcam Logitech C920', 'Webcam Full HD 1080p com microfone embutido, USB', 299.90, 2, 4),
('Notebook Samsung Galaxy Book3', 'Notebook Samsung Galaxy Book3, Intel Core i5, 8GB RAM, 256GB SSD', 3499.99, 5, 1),
('Mouse Logitech G502', 'Mouse gamer com RGB e 11 botões programáveis', 249.90, 2, 2),
('Monitor LG 27" 4K', 'Monitor 4K UHD 27 polegadas, IPS, HDR10', 1899.00, 4, 3);

-- ============================================================
-- Inserir SKUs de exemplo (variações dos produtos)
-- ============================================================
INSERT INTO skus (code, price, stock, product_id) VALUES
-- SKUs do Notebook Dell Inspiron 15
('NB-DELL-001-PRATA', 4999.99, 50, 1),
('NB-DELL-001-PRETO', 4999.99, 30, 1),
('NB-DELL-001-CINZA', 5199.99, 20, 1),
-- SKUs do Mouse Logitech MX Master 3
('MO-LOG-001-PRETO', 399.90, 100, 2),
('MO-LOG-001-BRANCO', 399.90, 80, 2),
('MO-LOG-001-GRAFITE', 419.90, 60, 2),
-- SKUs do Teclado Keychron K2
('TK-KEY-001-BLUE', 549.00, 40, 3),
('TK-KEY-001-RED', 579.00, 30, 3),
('TK-KEY-001-BROWN', 569.00, 35, 3),
-- SKUs do Monitor LG UltraWide
('MN-LG-001-34', 2899.00, 25, 4),
-- SKUs da Webcam Logitech C920
('WC-LOG-001-PRETO', 299.90, 70, 5),
('WC-LOG-001-BRANCO', 309.90, 40, 5),
-- SKUs do Notebook Samsung Galaxy Book3
('NB-SAM-001-PRATA', 3499.99, 35, 6),
('NB-SAM-001-PRATA-8GB', 3699.99, 25, 6),
-- SKUs do Mouse Logitech G502
('MO-LOG-002-PRETO', 249.90, 90, 7),
('MO-LOG-002-BRANCO', 259.90, 50, 7),
-- SKUs do Monitor LG 27" 4K
('MN-LG-002-27', 1899.00, 30, 8);

-- ============================================================
-- Fim do script
-- ============================================================
