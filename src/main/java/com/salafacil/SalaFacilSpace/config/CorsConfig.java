package com.salafacil.SalaFacilSpace.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration // Marca essa classe como uma configuração do Spring
public class CorsConfig implements WebMvcConfigurer { // Permite personalizar o comportamento do Spring MVC

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // Aplica essa configuração para todas as rotas da aplicação

            // Define quais origens (domínios) podem acessar a API
            // "*" permite todas as origens (ideal só para testes)
            // Para trabalhar com autenticação (como JWT), declare o domínio do front-end explicitamente
            .allowedOrigins("http://192.168.1.56:8081") // <- coloque o domínio do seu front-end aqui

            // Métodos HTTP permitidos na API
            .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")

            // Cabeçalhos permitidos nas requisições (Authorization é necessário para JWT)
            .allowedHeaders("*")

            // Permite envio de cookies e headers de autenticação (como Authorization: Bearer <token>)
            // Só pode ser usado se allowedOrigins for um domínio específico (e não "*")
            .allowCredentials(true); // <- importante se o front usar "withCredentials" ou enviar tokens JWT
    }
}
