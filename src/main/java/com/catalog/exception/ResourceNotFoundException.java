package com.catalog.exception;

/**
 * Exceção personalizada para recursos não encontrados.
 *
 * Esta exceção é lançada quando um recurso solicitado (ex: produto) não é encontrado
 * no banco de dados. Ela é capturada pelo GlobalExceptionHandler e convertida
 * em uma resposta HTTP 404 Not Found com uma mensagem descritiva.
 *
 * Exemplo de uso:
 *   throw new ResourceNotFoundException("Produto não encontrado com id: 5");
 *
 * A mensagem passada ao construtor será retornada ao cliente na resposta HTTP.
 */
public class ResourceNotFoundException extends RuntimeException {

    /**
     * Construtor que recebe a mensagem de erro.
     *
     * @param message mensagem descritiva do erro (ex: "Produto não encontrado com id: 5")
     */
    public ResourceNotFoundException(String message) {
        super(message); // Chama o construtor da classe pai (RuntimeException)
    }
}
