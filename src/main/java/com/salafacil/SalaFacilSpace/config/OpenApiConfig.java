package com.salafacil.SalaFacilSpace.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuração do OpenAPI/Swagger para documentação da API Sala Fácil.
 * Define informações básicas e esquema de segurança JWT Bearer.
 */
@Configuration
public class OpenApiConfig {

    private static final String SECURITY_SCHEME_NAME = "bearerAuth";
    private static final String SECURITY_SCHEME_TYPE = "bearer";
    private static final String BEARER_FORMAT = "JWT";

    @Value("${api.info.title:Sala Fácil API}")
    private String apiTitle;

    @Value("${api.info.description:Documentação da API Sala Fácil}")
    private String apiDescription;

    @Value("${api.info.version:1.0}")
    private String apiVersion;

    /**
     * Configura o bean OpenAPI para fornecer documentação automática da API.
     * Define título, descrição, versão e esquema de segurança Bearer JWT.
     */
    @Bean
    public OpenAPI api() {
        return new OpenAPI()
            .info(new Info()
                .title(apiTitle)
                .description(apiDescription)
                .version(apiVersion))
            .addSecurityItem(new SecurityRequirement().addList(SECURITY_SCHEME_NAME))
            .components(new Components()
                .addSecuritySchemes(SECURITY_SCHEME_NAME,
                    new SecurityScheme()
                        .name(SECURITY_SCHEME_NAME)
                        .type(SecurityScheme.Type.HTTP)
                        .scheme(SECURITY_SCHEME_TYPE)
                        .bearerFormat(BEARER_FORMAT)));
    }
}
