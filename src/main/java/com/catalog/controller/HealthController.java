package com.catalog.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * Controller para endpoints de verificação de saúde da aplicação.
 *
 * Este controller fornece endpoints para monitoramento da API,
 * úteis para load balancers e ferramentas de observabilidade.
 */
@RestController
@RequestMapping("/api")
public class HealthController {

    /**
     * Verifica se a API está funcionando.
     *
     * GET /api/health
     *
     * @return ResponseEntity com status da aplicação
     */
    @GetMapping("/health")
    public ResponseEntity<Map<String, Object>> health() {
        Map<String, Object> response = new HashMap<>();
        response.put("status", "UP");
        response.put("timestamp", LocalDateTime.now());
        response.put("service", "product-catalog-api");
        response.put("version", "1.1.0");
        return ResponseEntity.ok(response);
    }
}
