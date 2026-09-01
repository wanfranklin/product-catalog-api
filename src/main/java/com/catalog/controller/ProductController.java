package com.catalog.controller;

import com.catalog.dto.ProductRequest;
import com.catalog.dto.ProductResponse;
import com.catalog.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
 *
 * Suporte a paginação:
 *   - GET /api/products?page=0&size=10&sort=name,asc
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
     * Lista todos os produtos cadastrados com suporte a paginação.
     *
     * GET /api/products?page=0&size=10&sort=name,asc
     *
     * @param page número da página (padrão: 0)
     * @param size tamanho da página (padrão: 10)
     * @param sort parâmetro de ordenação (padrão: id,asc)
     * @return ResponseEntity com página de ProductResponse
     */
    @GetMapping
    public ResponseEntity<Page<ProductResponse>> findAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id,asc") String[] sort) {

        String[] sortParts = sort[0].split(",");
        Sort.Direction direction = sortParts.length > 1 && sortParts[1].equalsIgnoreCase("desc")
                ? Sort.Direction.DESC
                : Sort.Direction.ASC;
        Sort sortObj = Sort.by(direction, sortParts[0]);

        Pageable pageable = PageRequest.of(page, size, sortObj);
        return ResponseEntity.ok(productService.findAll(pageable));
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
     * Lista todos os produtos de uma marca específica com paginação.
     *
     * GET /api/products/brand/{brandId}?page=0&size=10
     *
     * @param brandId identificador da marca
     * @param page    número da página (padrão: 0)
     * @param size    tamanho da página (padrão: 10)
     * @return ResponseEntity com página de ProductResponse da marca
     */
    @GetMapping("/brand/{brandId}")
    public ResponseEntity<Page<ProductResponse>> findByBrandId(
            @PathVariable Long brandId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(productService.findByBrandId(brandId, pageable));
    }

    /**
     * Lista todos os produtos de uma categoria específica com paginação.
     *
     * GET /api/products/category/{categoryId}?page=0&size=10
     *
     * @param categoryId identificador da categoria
     * @param page       número da página (padrão: 0)
     * @param size       tamanho da página (padrão: 10)
     * @return ResponseEntity com página de ProductResponse da categoria
     */
    @GetMapping("/category/{categoryId}")
    public ResponseEntity<Page<ProductResponse>> findByCategoryId(
            @PathVariable Long categoryId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(productService.findByCategoryId(categoryId, pageable));
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
