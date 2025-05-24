package com.salafacil.SalaFacilSpace.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Configuração de CORS para controlar quais origens, métodos e headers
 * podem acessar a API.  
 * 
 * Para segurança, as origens permitidas são parametrizadas via properties,
 * evitando liberar o mundo inteiro (a não ser que explicitamente desejado em dev).
 * 
 * Ideal usar perfis Spring para controlar ambientes (dev, prod).
 */
@Configuration
public class CorsConfig implements WebMvcConfigurer {

    // Origem permitida, configurável via application.properties
    @Value("${app.cors.allowed-origins}")
    private String[] allowedOrigins;

    // Métodos HTTP permitidos, configuráveis caso necessário
    private static final String[] ALLOWED_METHODS = { "GET", "POST", "PUT", "DELETE", "OPTIONS" };

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
            .allowedOrigins(allowedOrigins) // Origem configurável para segurança
            .allowedMethods(ALLOWED_METHODS)
            .allowedHeaders("*");
            // Se precisar permitir cookies/autenticação, descomente a linha abaixo
            //.allowCredentials(true);
    }
}
