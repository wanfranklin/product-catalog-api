package com.catalog.service;

import com.catalog.dto.CategoryRequest;
import com.catalog.dto.CategoryResponse;
import com.catalog.entity.Category;
import com.catalog.exception.ResourceNotFoundException;
import com.catalog.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Serviço de negócio para gerenciamento de categorias.
 *
 * Responsabilidades:
 *   - Listar todas as categorias cadastradas
 *   - Buscar uma categoria específica por ID
 *   - Criar uma nova categoria
 *   - Atualizar os dados de uma categoria existente
 *   - Excluir uma categoria do catálogo
 */
@Service
public class CategoryService {

    /** Repositório de categorias injetado via construtor. */
    private final CategoryRepository categoryRepository;

    /**
     * Construtor que recebe o repositório de categorias.
     *
     * @param categoryRepository repositório de categorias para operações de banco
     */
    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    /**
     * Lista todas as categorias cadastradas.
     *
     * @return lista de todas as categorias convertidas para CategoryResponse
     */
    public List<CategoryResponse> findAll() {
        return categoryRepository.findAll().stream()
                .map(CategoryResponse::fromEntity)
                .toList();
    }

    /**
     * Busca uma categoria pelo seu identificador único (ID).
     *
     * @param id identificador da categoria a ser buscada
     * @return categoria encontrada convertida para CategoryResponse
     * @throws ResourceNotFoundException se a categoria não existir no banco
     */
    public CategoryResponse findById(Long id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Categoria não encontrada com id: " + id));
        return CategoryResponse.fromEntity(category);
    }

    /**
     * Cria uma nova categoria no catálogo.
     *
     * @param request dados da categoria a ser criada
     * @return categoria criada convertida para CategoryResponse
     */
    public CategoryResponse create(CategoryRequest request) {
        Category category = new Category();
        category.setName(request.getName());
        category.setDescription(request.getDescription());
        Category saved = categoryRepository.save(category);
        return CategoryResponse.fromEntity(saved);
    }

    /**
     * Atualiza os dados de uma categoria existente.
     *
     * @param id      identificador da categoria a ser atualizada
     * @param request novos dados da categoria
     * @return categoria atualizada convertida para CategoryResponse
     * @throws ResourceNotFoundException se a categoria não existir no banco
     */
    public CategoryResponse update(Long id, CategoryRequest request) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Categoria não encontrada com id: " + id));
        category.setName(request.getName());
        category.setDescription(request.getDescription());
        Category updated = categoryRepository.save(category);
        return CategoryResponse.fromEntity(updated);
    }

    /**
     * Exclui uma categoria do catálogo.
     *
     * @param id identificador da categoria a ser excluída
     * @throws ResourceNotFoundException se a categoria não existir no banco
     */
    public void delete(Long id) {
        if (!categoryRepository.existsById(id)) {
            throw new ResourceNotFoundException("Categoria não encontrada com id: " + id);
        }
        categoryRepository.deleteById(id);
    }
}
