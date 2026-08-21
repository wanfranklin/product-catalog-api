package com.catalog.controller;

import com.catalog.dto.SkuRequest;
import com.catalog.dto.SkuResponse;
import com.catalog.service.SkuService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller REST para operações de CRUD de SKUs.
 *
 * Esta classe expõe endpoints HTTP para gerenciar SKUs (Stock Keeping Units).
 * Cada SKU representa uma variação de um produto com código, preço e estoque próprio.
 *
 * Endpoints disponíveis:
 *   - GET    /api/skus         → Listar todos os SKUs
 *   - GET    /api/skus/{id}    → Buscar SKU por ID
 *   - GET    /api/skus/product/{productId} → Listar SKUs por produto
 *   - POST   /api/skus         → Criar um novo SKU
 *   - PUT    /api/skus/{id}    → Atualizar SKU por ID
 *   - DELETE /api/skus/{id}    → Excluir SKU por ID
 *
 * Padrões aplicados:
 *   - RESTful: Uso correto de verbos HTTP e códigos de status
 *   - Validação: @Valid para validar dados de entrada
 *   - Tratamento de erros: Via GlobalExceptionHandler
 *   - Injeção de dependência: Via construtor
 */
@RestController
@RequestMapping("/api/skus")
public class SkuController {

    private final SkuService skuService;

    /**
     * Construtor com injeção de dependência do serviço.
     *
     * @param skuService serviço de SKUs
     */
    public SkuController(SkuService skuService) {
        this.skuService = skuService;
    }

    /**
     * Lista todos os SKUs cadastrados.
     *
     * @return ResponseEntity com lista de SkuResponse e status 200 OK
     */
    @GetMapping
    public ResponseEntity<List<SkuResponse>> findAll() {
        List<SkuResponse> skus = skuService.findAll();
        return ResponseEntity.ok(skus);
    }

    /**
     * Busca um SKU pelo seu identificador.
     *
     * @param id identificador do SKU
     * @return ResponseEntity com SkuResponse e status 200 OK,
     *         ou status 404 Not Found se não encontrar
     */
    @GetMapping("/{id}")
    public ResponseEntity<SkuResponse> findById(@PathVariable Long id) {
        SkuResponse sku = skuService.findById(id);
        return ResponseEntity.ok(sku);
    }

    /**
     * Lista todos os SKUs de um produto específico.
     *
     * @param productId ID do produto
     * @return ResponseEntity com lista de SkuResponse e status 200 OK
     */
    @GetMapping("/product/{productId}")
    public ResponseEntity<List<SkuResponse>> findByProductId(@PathVariable Long productId) {
        List<SkuResponse> skus = skuService.findByProductId(productId);
        return ResponseEntity.ok(skus);
    }

    /**
     * Cria um novo SKU.
     *
     * @param request dados do SKU a ser criado (validados via @Valid)
     * @return ResponseEntity com SkuResponse e status 201 Created
     */
    @PostMapping
    public ResponseEntity<SkuResponse> create(@Valid @RequestBody SkuRequest request) {
        SkuResponse createdSku = skuService.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdSku);
    }

    /**
     * Atualiza um SKU existente.
     *
     * @param id      identificador do SKU a ser atualizado
     * @param request novos dados do SKU (validados via @Valid)
     * @return ResponseEntity com SkuResponse atualizado e status 200 OK,
     *         ou status 404 Not Found se não encontrar
     */
    @PutMapping("/{id}")
    public ResponseEntity<SkuResponse> update(@PathVariable Long id, @Valid @RequestBody SkuRequest request) {
        SkuResponse updatedSku = skuService.update(id, request);
        return ResponseEntity.ok(updatedSku);
    }

    /**
     * Exclui um SKU pelo seu identificador.
     *
     * @param id identificador do SKU a ser excluído
     * @return ResponseEntity com status 204 No Content,
     *         ou status 404 Not Found se não encontrar
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        skuService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
