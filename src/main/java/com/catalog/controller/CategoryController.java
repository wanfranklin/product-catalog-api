package com.catalog.controller;

import com.catalog.dto.CategoryRequest;
import com.catalog.dto.CategoryResponse;
import com.catalog.service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller REST para gerenciamento de categorias.
 *
 * Expõe os endpoints HTTP para operações CRUD de categorias.
 */
@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    /** Serviço de categorias injetado via construtor. */
    private final CategoryService categoryService;

    /**
     * Construtor que recebe o serviço de categorias.
     *
     * @param categoryService serviço de categorias para operações de negócio
     */
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    /**
     * Lista todas as categorias cadastradas.
     *
     * GET /api/categories
     *
     * @return ResponseEntity com lista de CategoryResponse
     */
    @GetMapping
    public ResponseEntity<List<CategoryResponse>> findAll() {
        return ResponseEntity.ok(categoryService.findAll());
    }

    /**
     * Busca uma categoria pelo seu ID.
     *
     * GET /api/categories/{id}
     *
     * @param id identificador da categoria a ser buscada
     * @return ResponseEntity com o CategoryResponse encontrado
     */
    @GetMapping("/{id}")
    public ResponseEntity<CategoryResponse> findById(@PathVariable Long id) {
        return ResponseEntity.ok(categoryService.findById(id));
    }

    /**
     * Cria uma nova categoria no catálogo.
     *
     * POST /api/categories
     *
     * @param request dados da categoria a ser criada
     * @return ResponseEntity com o CategoryResponse criado e status 201 Created
     */
    @PostMapping
    public ResponseEntity<CategoryResponse> create(@Valid @RequestBody CategoryRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(categoryService.create(request));
    }

    /**
     * Atualiza uma categoria existente.
     *
     * PUT /api/categories/{id}
     *
     * @param id      identificador da categoria a ser atualizada
     * @param request novos dados da categoria
     * @return ResponseEntity com o CategoryResponse atualizado
     */
    @PutMapping("/{id}")
    public ResponseEntity<CategoryResponse> update(@PathVariable Long id, @Valid @RequestBody CategoryRequest request) {
        return ResponseEntity.ok(categoryService.update(id, request));
    }

    /**
     * Exclui uma categoria do catálogo.
     *
     * DELETE /api/categories/{id}
     *
     * @param id identificador da categoria a ser excluída
     * @return ResponseEntity vazio com status 204 No Content
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        categoryService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
