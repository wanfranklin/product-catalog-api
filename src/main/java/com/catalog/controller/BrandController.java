package com.catalog.controller;

import com.catalog.dto.BrandRequest;
import com.catalog.dto.BrandResponse;
import com.catalog.service.BrandService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller REST para gerenciamento de marcas.
 *
 * Expõe os endpoints HTTP para operações CRUD de marcas.
 */
@RestController
@RequestMapping("/api/brands")
public class BrandController {

    /** Serviço de marcas injetado via construtor. */
    private final BrandService brandService;

    /**
     * Construtor que recebe o serviço de marcas.
     *
     * @param brandService serviço de marcas para operações de negócio
     */
    public BrandController(BrandService brandService) {
        this.brandService = brandService;
    }

    /**
     * Lista todas as marcas cadastradas.
     *
     * GET /api/brands
     *
     * @return ResponseEntity com lista de BrandResponse
     */
    @GetMapping
    public ResponseEntity<List<BrandResponse>> findAll() {
        return ResponseEntity.ok(brandService.findAll());
    }

    /**
     * Busca uma marca pelo seu ID.
     *
     * GET /api/brands/{id}
     *
     * @param id identificador da marca a ser buscada
     * @return ResponseEntity com o BrandResponse encontrado
     */
    @GetMapping("/{id}")
    public ResponseEntity<BrandResponse> findById(@PathVariable Long id) {
        return ResponseEntity.ok(brandService.findById(id));
    }

    /**
     * Cria uma nova marca no catálogo.
     *
     * POST /api/brands
     *
     * @param request dados da marca a ser criada
     * @return ResponseEntity com o BrandResponse criado e status 201 Created
     */
    @PostMapping
    public ResponseEntity<BrandResponse> create(@Valid @RequestBody BrandRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(brandService.create(request));
    }

    /**
     * Atualiza uma marca existente.
     *
     * PUT /api/brands/{id}
     *
     * @param id      identificador da marca a ser atualizada
     * @param request novos dados da marca
     * @return ResponseEntity com o BrandResponse atualizado
     */
    @PutMapping("/{id}")
    public ResponseEntity<BrandResponse> update(@PathVariable Long id, @Valid @RequestBody BrandRequest request) {
        return ResponseEntity.ok(brandService.update(id, request));
    }

    /**
     * Exclui uma marca do catálogo.
     *
     * DELETE /api/brands/{id}
     *
     * @param id identificador da marca a ser excluída
     * @return ResponseEntity vazio com status 204 No Content
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        brandService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
