package com.salafacil.SalaFacilSpace.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration // Marca essa classe como uma classe de configuração do Spring
public class CorsConfig implements WebMvcConfigurer {

    @Override //segurança de que você tá sobrescrevendo certo.
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // Aplica a configuração CORS para todas as rotas
            .allowedOrigins("*") // Permite requisições de qualquer origem (frontend). Em produção, substitua pelo domínio específico
            .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // Métodos HTTP permitidos
            .allowedHeaders("*"); // Permite qualquer cabeçalho HTTP
    }
}
