package com.salafacil.SalaFacilSpace.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig2 {

    @Bean
    public OpenAPI api() {
        return new OpenAPI()
            .info(new Info()
                .title("Sala Fácil API")
                .description("Documentação da API Sala Fácil")
                .version("1.0"));
    }
}
