package com.catalog.service;

import com.catalog.dto.ProductRequest;
import com.catalog.dto.ProductResponse;
import com.catalog.entity.Brand;
import com.catalog.entity.Category;
import com.catalog.entity.Product;
import com.catalog.exception.ResourceNotFoundException;
import com.catalog.repository.BrandRepository;
import com.catalog.repository.CategoryRepository;
import com.catalog.repository.ProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * Serviço de negócio para gerenciamento de produtos.
 *
 * Esta classe contém toda a lógica de negócio relacionada aos produtos.
 * Ela atua como intermediária entre o Controller (camada de apresentação)
 * e o Repository (camada de persistência).
 *
 * Responsabilidades:
 *   - Listar todos os produtos cadastrados (com paginação)
 *   - Buscar um produto específico por ID
 *   - Criar um novo produto (com validação de marca e categoria)
 *   - Atualizar os dados de um produto existente
 *   - Excluir um produto do catálogo
 *   - Buscar produtos por marca (com paginação)
 *   - Buscar produtos por categoria (com paginação)
 *
 * A anotação @Service indica que esta classe é um serviço Spring,
 * permitindo a injeção de dependências e o gerenciamento pelo container.
 */
@Service
public class ProductService {

    /** Repositório de produtos injetado via construtor. */
    private final ProductRepository productRepository;

    /** Repositório de marcas injetado via construtor. */
    private final BrandRepository brandRepository;

    /** Repositório de categorias injetado via construtor. */
    private final CategoryRepository categoryRepository;

    /**
     * Construtor que recebe os repositórios necessários.
     *
     * @param productRepository repositório de produtos
     * @param brandRepository   repositório de marcas
     * @param categoryRepository repositório de categorias
     */
    public ProductService(ProductRepository productRepository, BrandRepository brandRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.brandRepository = brandRepository;
        this.categoryRepository = categoryRepository;
    }

    /**
     * Lista todos os produtos cadastrados no catálogo com paginação.
     *
     * @param pageable informações de paginação (página, tamanho, ordenação)
     * @return página de produtos convertidos para ProductResponse
     */
    public Page<ProductResponse> findAll(Pageable pageable) {
        return productRepository.findAll(pageable)
                .map(ProductResponse::fromEntity);
    }

    /**
     * Busca um produto pelo seu identificador único (ID).
     *
     * @param id identificador do produto a ser buscado
     * @return produto encontrado convertido para ProductResponse
     * @throws ResourceNotFoundException se o produto não existir no banco
     */
    public ProductResponse findById(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Produto não encontrado com id: " + id));
        return ProductResponse.fromEntity(product);
    }

    /**
     * Lista todos os produtos de uma marca específica com paginação.
     *
     * @param brandId  identificador da marca
     * @param pageable informações de paginação
     * @return página de produtos da marca convertidos para ProductResponse
     * @throws ResourceNotFoundException se a marca não existir no banco
     */
    public Page<ProductResponse> findByBrandId(Long brandId, Pageable pageable) {
        // Verifica se a marca existe
        if (!brandRepository.existsById(brandId)) {
            throw new ResourceNotFoundException("Marca não encontrada com id: " + brandId);
        }
        return productRepository.findByBrandId(brandId, pageable)
                .map(ProductResponse::fromEntity);
    }

    /**
     * Lista todos os produtos de uma categoria específica com paginação.
     *
     * @param categoryId identificador da categoria
     * @param pageable   informações de paginação
     * @return página de produtos da categoria convertidos para ProductResponse
     * @throws ResourceNotFoundException se a categoria não existir no banco
     */
    public Page<ProductResponse> findByCategoryId(Long categoryId, Pageable pageable) {
        // Verifica se a categoria existe
        if (!categoryRepository.existsById(categoryId)) {
            throw new ResourceNotFoundException("Categoria não encontrada com id: " + categoryId);
        }
        return productRepository.findByCategoryId(categoryId, pageable)
                .map(ProductResponse::fromEntity);
    }

    /**
     * Cria um novo produto no catálogo.
     *
     * Recebe os dados do produto via ProductRequest, busca a marca e categoria
     * pelo ID fornecido, cria uma nova entidade Product, salva no banco de dados
     * e retorna o produto criado com o ID gerado.
     *
     * @param request dados do produto a ser criado (vindos do JSON da requisição)
     * @return produto criado convertido para ProductResponse
     * @throws ResourceNotFoundException se a marca ou categoria não existirem
     */
    public ProductResponse create(ProductRequest request) {
        // Busca a marca pelo ID
        Brand brand = brandRepository.findById(request.getBrandId())
                .orElseThrow(() -> new ResourceNotFoundException("Marca não encontrada com id: " + request.getBrandId()));

        // Busca a categoria pelo ID
        Category category = categoryRepository.findById(request.getCategoryId())
                .orElseThrow(() -> new ResourceNotFoundException("Categoria não encontrada com id: " + request.getCategoryId()));

        // Cria uma nova entidade Product a partir dos dados do request
        Product product = new Product();
        product.setName(request.getName());
        product.setDescription(request.getDescription());
        product.setPrice(request.getPrice());
        product.setBrand(brand);
        product.setCategory(category);

        // Salva no banco de dados (o ID é gerado automaticamente)
        Product saved = productRepository.save(product);

        // Converte e retorna o produto salvo
        return ProductResponse.fromEntity(saved);
    }

    /**
     * Atualiza os dados de um produto existente.
     *
     * @param id      identificador do produto a ser atualizado
     * @param request novos dados do produto
     * @return produto atualizado convertido para ProductResponse
     * @throws ResourceNotFoundException se o produto, marca ou categoria não existirem
     */
    public ProductResponse update(Long id, ProductRequest request) {
        // Busca o produto existente no banco
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Produto não encontrado com id: " + id));

        // Busca a marca pelo ID
        Brand brand = brandRepository.findById(request.getBrandId())
                .orElseThrow(() -> new ResourceNotFoundException("Marca não encontrada com id: " + request.getBrandId()));

        // Busca a categoria pelo ID
        Category category = categoryRepository.findById(request.getCategoryId())
                .orElseThrow(() -> new ResourceNotFoundException("Categoria não encontrada com id: " + request.getCategoryId()));

        // Atualiza todos os campos com os novos valores
        product.setName(request.getName());
        product.setDescription(request.getDescription());
        product.setPrice(request.getPrice());
        product.setBrand(brand);
        product.setCategory(category);

        // Salva as alterações no banco de dados
        Product updated = productRepository.save(product);

        // Converte e retorna o produto atualizado
        return ProductResponse.fromEntity(updated);
    }

    /**
     * Exclui um produto do catálogo.
     *
     * @param id identificador do produto a ser excluído
     * @throws ResourceNotFoundException se o produto não existir no banco
     */
    public void delete(Long id) {
        // Verifica se o produto existe antes de excluir
        if (!productRepository.existsById(id)) {
            throw new ResourceNotFoundException("Produto não encontrado com id: " + id);
        }
        // Exclui o produto do banco de dados
        productRepository.deleteById(id);
    }
}
