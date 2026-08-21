package com.catalog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Classe principal da aplicação Product Catalog API.
 *
 * Esta classe é o ponto de entrada da aplicação Spring Boot.
 * A anotação @SpringBootApplication combina as seguintes anotações:
 *   - @EnableAutoConfiguration: habilita a configuração automática do Spring Boot
 *   - @ComponentScan: escaneia o pacote atual e seus subpacotes por componentes Spring
 *   - @Configuration: marca esta classe como fonte de definições de beans
 *
 * O método main() inicia o servidor web embarcado (Tomcat) na porta configurada (8080).
 */
@SpringBootApplication
public class ProductCatalogApplication {

    /**
     * Método principal que inicializa a aplicação Spring Boot.
     *
     * @param args argumentos de linha de comando passados para a aplicação
     */
    public static void main(String[] args) {
        SpringApplication.run(ProductCatalogApplication.class, args);
    }
}
