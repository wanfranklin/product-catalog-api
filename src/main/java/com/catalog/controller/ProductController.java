package com.catalog.controller;

import com.catalog.dto.ProductRequest;
import com.catalog.dto.ProductResponse;
import com.catalog.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller REST para gerenciamento de produtos.
 *
 * Esta classe expõe os endpoints HTTP para operações CRUD de produtos.
 * Utiliza o padrão RESTful com os seguintes verbos HTTP:
 *   - GET: listar/buscar recursos
 *   - POST: criar novos recursos
 *   - PUT: atualizar recursos existentes
 *   - DELETE: excluir recursos
 *
 * Endpoints adicionais:
 *   - GET /api/products/brand/{brandId}: listar produtos por marca
 *   - GET /api/products/category/{categoryId}: listar produtos por categoria
 */
@RestController
@RequestMapping("/api/products")
public class ProductController {

    /** Serviço de produtos injetado via construtor. */
    private final ProductService productService;

    /**
     * Construtor que recebe o serviço de produtos.
     *
     * @param productService serviço de produtos para operações de negócio
     */
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    /**
     * Lista todos os produtos cadastrados.
     *
     * GET /api/products
     *
     * @return ResponseEntity com lista de ProductResponse
     */
    @GetMapping
    public ResponseEntity<List<ProductResponse>> findAll() {
        return ResponseEntity.ok(productService.findAll());
    }

    /**
     * Busca um produto pelo seu ID.
     *
     * GET /api/products/{id}
     *
     * @param id identificador do produto a ser buscado
     * @return ResponseEntity com o ProductResponse encontrado
     */
    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> findById(@PathVariable Long id) {
        return ResponseEntity.ok(productService.findById(id));
    }

    /**
     * Lista todos os produtos de uma marca específica.
     *
     * GET /api/products/brand/{brandId}
     *
     * @param brandId identificador da marca
     * @return ResponseEntity com lista de ProductResponse da marca
     */
    @GetMapping("/brand/{brandId}")
    public ResponseEntity<List<ProductResponse>> findByBrandId(@PathVariable Long brandId) {
        return ResponseEntity.ok(productService.findByBrandId(brandId));
    }

    /**
     * Lista todos os produtos de uma categoria específica.
     *
     * GET /api/products/category/{categoryId}
     *
     * @param categoryId identificador da categoria
     * @return ResponseEntity com lista de ProductResponse da categoria
     */
    @GetMapping("/category/{categoryId}")
    public ResponseEntity<List<ProductResponse>> findByCategoryId(@PathVariable Long categoryId) {
        return ResponseEntity.ok(productService.findByCategoryId(categoryId));
    }

    /**
     * Cria um novo produto no catálogo.
     *
     * POST /api/products
     *
     * @param request dados do produto a ser criado
     * @return ResponseEntity com o ProductResponse criado e status 201 Created
     */
    @PostMapping
    public ResponseEntity<ProductResponse> create(@Valid @RequestBody ProductRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(productService.create(request));
    }

    /**
     * Atualiza um produto existente.
     *
     * PUT /api/products/{id}
     *
     * @param id      identificador do produto a ser atualizado
     * @param request novos dados do produto
     * @return ResponseEntity com o ProductResponse atualizado
     */
    @PutMapping("/{id}")
    public ResponseEntity<ProductResponse> update(@PathVariable Long id, @Valid @RequestBody ProductRequest request) {
        return ResponseEntity.ok(productService.update(id, request));
    }

    /**
     * Exclui um produto do catálogo.
     *
     * DELETE /api/products/{id}
     *
     * @param id identificador do produto a ser excluído
     * @return ResponseEntity vazio com status 204 No Content
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        productService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
