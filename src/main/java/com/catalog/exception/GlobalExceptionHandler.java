package com.catalog.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Handler global de exceções para a aplicação.
 *
 * Esta classe captura exceções lançadas pelos controllers e as converte
 * em respostas HTTP padronizadas com mensagens descritivas.
 *
 * A anotação @RestControllerAdvice combina @ControllerAdvice e @ResponseBody,
 * permitindo que os métodos de tratamento de exceções retornem JSON diretamente.
 *
 * Exceções tratadas:
 *   - ResourceNotFoundException -> HTTP 404 Not Found
 *   - MethodArgumentNotValidException -> HTTP 400 Bad Request (erros de validação)
 *   - IllegalArgumentException -> HTTP 400 Bad Request (argumentos inválidos)
 *
 * Formato padrão de resposta de erro:
 * {
 *   "timestamp": "2026-08-21T10:30:00",
 *   "status": 404,
 *   "error": "Not Found",
 *   "message": "Produto não encontrado com id: 5"
 * }
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Trata a exceção ResourceNotFoundException (recurso não encontrado).
     *
     * Quando um controller tenta buscar um produto que não existe,
     * esta exceção é lançada e capturada por este método, que retorna
     * uma resposta HTTP 404 Not Found.
     *
     * @param ex exceção lançada com a mensagem de erro
     * @return ResponseEntity com o corpo de erro em formato JSON
     */
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Map<String, Object>> handleNotFound(ResourceNotFoundException ex) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now()); // Data/hora do erro
        body.put("status", HttpStatus.NOT_FOUND.value()); // Código HTTP 404
        body.put("error", "Não Encontrado"); // Descrição do erro
        body.put("message", ex.getMessage()); // Mensagem detalhada
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(body);
    }

    /**
     * Trata erros de validação dos DTOs (MethodArgumentNotValidException).
     *
     * Quando os dados enviados na requisição não atendem as regras de validação
     * definidas nas anotações do DTO (NotBlank, NotNull, DecimalMin, etc.),
     * o Spring lança esta exceção com todos os erros de validação encontrados.
     *
     * Este método concatena todas as mensagens de erro separadas por vírgula
     * e retorna uma resposta HTTP 400 Bad Request.
     *
     * Exemplo de erro retornado:
     * "Nome do produto é obrigatório, Preço do produto é obrigatório"
     *
     * @param ex exceção contendo todos os erros de validação
     * @return ResponseEntity com o corpo de erro em formato JSON
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> handleValidation(MethodArgumentNotValidException ex) {
        // Concatena todas as mensagens de erro de validação em uma única string
        String errors = ex.getBindingResult().getFieldErrors().stream()
                .map(e -> e.getField() + ": " + e.getDefaultMessage())
                .collect(Collectors.joining(", "));

        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("status", HttpStatus.BAD_REQUEST.value()); // Código HTTP 400
        body.put("error", "Falha na Validação");
        body.put("message", errors);
        return ResponseEntity.badRequest().body(body);
    }

    /**
     * Trata argumentos ilegais na requisição.
     *
     * Quando um método de negócio lança IllegalArgumentException,
     * esta exceção é capturada e retornada como HTTP 400 Bad Request.
     *
     * @param ex exceção lançada com a mensagem de erro
     * @return ResponseEntity com o corpo de erro em formato JSON
     */
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Map<String, Object>> handleIllegalArgument(IllegalArgumentException ex) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("status", HttpStatus.BAD_REQUEST.value());
        body.put("error", "Requisição Inválida");
        body.put("message", ex.getMessage());
        return ResponseEntity.badRequest().body(body);
    }
}
