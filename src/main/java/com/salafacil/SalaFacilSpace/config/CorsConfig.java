package com.salafacil.SalaFacilSpace.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // Aplica essa configuração para todas as rotas da aplicação
            .allowedOriginPatterns("*") // permite qualquer origem com qualquer porta
            .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
            .allowedHeaders("*")
            .allowCredentials(true); // funciona com JWT e cookies se origem for específica ou padrão permitido
    }
}
