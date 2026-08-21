package com.catalog.service;

import com.catalog.dto.BrandRequest;
import com.catalog.dto.BrandResponse;
import com.catalog.entity.Brand;
import com.catalog.exception.ResourceNotFoundException;
import com.catalog.repository.BrandRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Serviço de negócio para gerenciamento de marcas.
 *
 * Responsabilidades:
 *   - Listar todas as marcas cadastradas
 *   - Buscar uma marca específica por ID
 *   - Criar uma nova marca
 *   - Atualizar os dados de uma marca existente
 *   - Excluir uma marca do catálogo
 */
@Service
public class BrandService {

    /** Repositório de marcas injetado via construtor. */
    private final BrandRepository brandRepository;

    /**
     * Construtor que recebe o repositório de marcas.
     *
     * @param brandRepository repositório de marcas para operações de banco
     */
    public BrandService(BrandRepository brandRepository) {
        this.brandRepository = brandRepository;
    }

    /**
     * Lista todas as marcas cadastradas.
     *
     * @return lista de todas as marcas convertidas para BrandResponse
     */
    public List<BrandResponse> findAll() {
        return brandRepository.findAll().stream()
                .map(BrandResponse::fromEntity)
                .toList();
    }

    /**
     * Busca uma marca pelo seu identificador único (ID).
     *
     * @param id identificador da marca a ser buscada
     * @return marca encontrada convertida para BrandResponse
     * @throws ResourceNotFoundException se a marca não existir no banco
     */
    public BrandResponse findById(Long id) {
        Brand brand = brandRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Marca não encontrada com id: " + id));
        return BrandResponse.fromEntity(brand);
    }

    /**
     * Cria uma nova marca no catálogo.
     *
     * @param request dados da marca a ser criada
     * @return marca criada convertida para BrandResponse
     */
    public BrandResponse create(BrandRequest request) {
        Brand brand = new Brand();
        brand.setName(request.getName());
        brand.setDescription(request.getDescription());
        Brand saved = brandRepository.save(brand);
        return BrandResponse.fromEntity(saved);
    }

    /**
     * Atualiza os dados de uma marca existente.
     *
     * @param id      identificador da marca a ser atualizada
     * @param request novos dados da marca
     * @return marca atualizada convertida para BrandResponse
     * @throws ResourceNotFoundException se a marca não existir no banco
     */
    public BrandResponse update(Long id, BrandRequest request) {
        Brand brand = brandRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Marca não encontrada com id: " + id));
        brand.setName(request.getName());
        brand.setDescription(request.getDescription());
        Brand updated = brandRepository.save(brand);
        return BrandResponse.fromEntity(updated);
    }

    /**
     * Exclui uma marca do catálogo.
     *
     * @param id identificador da marca a ser excluída
     * @throws ResourceNotFoundException se a marca não existir no banco
     */
    public void delete(Long id) {
        if (!brandRepository.existsById(id)) {
            throw new ResourceNotFoundException("Marca não encontrada com id: " + id);
        }
        brandRepository.deleteById(id);
    }
}
