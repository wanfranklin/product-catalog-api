package com.catalog.service;

import com.catalog.dto.SkuRequest;
import com.catalog.dto.SkuResponse;
import com.catalog.entity.Product;
import com.catalog.entity.Sku;
import com.catalog.exception.ResourceNotFoundException;
import com.catalog.repository.ProductRepository;
import com.catalog.repository.SkuRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Serviço para operações de negócio relacionadas a SKUs.
 *
 * Esta classe contém a lógica de negócio para CRUD de SKUs,
 * incluindo validações, conversões de DTO e tratamento de erros.
 *
 * Princípios aplicados:
 *   - Single Responsibility: Responsável apenas por lógica de SKU
 *   - Dependency Injection: Depende de repositórios via construtor
 *   - Service Layer: Camada de serviço entre Controller e Repository
 *
 * Relacionamento com Product:
 *   - Um SKU sempre pertence a um produto
 *   - Ao criar um SKU, o produto deve existir
 *   - Ao excluir um produto, seus SKUs são excluídos (cascade)
 */
@Service
public class SkuService {

    private final SkuRepository skuRepository;
    private final ProductRepository productRepository;

    /**
     * Construtor com injeção de dependência dos repositórios.
     *
     * @param skuRepository    repositório de SKUs
     * @param productRepository repositório de produtos
     */
    public SkuService(SkuRepository skuRepository, ProductRepository productRepository) {
        this.skuRepository = skuRepository;
        this.productRepository = productRepository;
    }

    /**
     * Lista todos os SKUs cadastrados.
     *
     * @return lista de SkuResponse com todos os SKUs
     */
    public List<SkuResponse> findAll() {
        return skuRepository.findAll()
                .stream()
                .map(SkuResponse::fromEntity)
                .collect(Collectors.toList());
    }

    /**
     * Busca um SKU pelo seu identificador.
     *
     * @param id identificador do SKU
     * @return SkuResponse com os dados do SKU encontrado
     * @throws ResourceNotFoundException se o SKU não for encontrado
     */
    public SkuResponse findById(Long id) {
        Sku sku = skuRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("SKU não encontrado com id: " + id));
        return SkuResponse.fromEntity(sku);
    }

    /**
     * Lista todos os SKUs de um produto específico.
     *
     * @param productId ID do produto
     * @return lista de SkuResponse do produto
     * @throws ResourceNotFoundException se o produto não for encontrado
     */
    public List<SkuResponse> findByProductId(Long productId) {
        if (!productRepository.existsById(productId)) {
            throw new ResourceNotFoundException("Produto não encontrado com id: " + productId);
        }
        return skuRepository.findByProductId(productId)
                .stream()
                .map(SkuResponse::fromEntity)
                .collect(Collectors.toList());
    }

    /**
     * Cria um novo SKU.
     *
     * Validações realizadas:
     *   - O produto associado deve existir
     *   - O código SKU deve ser único (constraint do banco)
     *
     * @param request dados do SKU a ser criado
     * @return SkuResponse com os dados do SKU criado
     * @throws ResourceNotFoundException se o produto não for encontrado
     */
    public SkuResponse create(SkuRequest request) {
        Product product = productRepository.findById(request.getProductId())
                .orElseThrow(() -> new ResourceNotFoundException("Produto não encontrado com id: " + request.getProductId()));

        Sku sku = new Sku();
        sku.setCode(request.getCode());
        sku.setPrice(request.getPrice());
        sku.setStock(request.getStock());
        sku.setProduct(product);

        Sku savedSku = skuRepository.save(sku);
        return SkuResponse.fromEntity(savedSku);
    }

    /**
     * Atualiza um SKU existente.
     *
     * Validações realizadas:
     *   - O SKU deve existir
     *   - O produto associado deve existir
     *   - O código SKU deve ser único (constraint do banco)
     *
     * @param id      identificador do SKU a ser atualizado
     * @param request novos dados do SKU
     * @return SkuResponse com os dados atualizados
     * @throws ResourceNotFoundException se o SKU ou produto não forem encontrados
     */
    public SkuResponse update(Long id, SkuRequest request) {
        Sku sku = skuRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("SKU não encontrado com id: " + id));

        Product product = productRepository.findById(request.getProductId())
                .orElseThrow(() -> new ResourceNotFoundException("Produto não encontrado com id: " + request.getProductId()));

        sku.setCode(request.getCode());
        sku.setPrice(request.getPrice());
        sku.setStock(request.getStock());
        sku.setProduct(product);

        Sku updatedSku = skuRepository.save(sku);
        return SkuResponse.fromEntity(updatedSku);
    }

    /**
     * Exclui um SKU pelo seu identificador.
     *
     * @param id identificador do SKU a ser excluído
     * @throws ResourceNotFoundException se o SKU não for encontrado
     */
    public void delete(Long id) {
        if (!skuRepository.existsById(id)) {
            throw new ResourceNotFoundException("SKU não encontrado com id: " + id);
        }
        skuRepository.deleteById(id);
    }
}
