package com.salafacil.SalaFacilSpace.config; 


import org.springframework.context.annotation.Configuration; // Permite marcar essa classe como configuração do Spring
import org.springframework.web.servlet.config.annotation.CorsRegistry; // Classe usada pra registrar as configurações de CORS
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer; // Interface que permite personalizar o comportamento do Spring MVC

@Configuration // Diz pro Spring: "Essa classe aqui é de configuração, use ela!"
public class CorsConfig implements WebMvcConfigurer { // Cria a classe CorsConfig e implementa a interface que permite alterar configs do Spring MVC

    @Override // Garante que você está sobrescrevendo corretamente o método addCorsMappings
    public void addCorsMappings(CorsRegistry registry) { // Método chamado automaticamente pelo Spring pra aplicar as regras de CORS
        
        registry.addMapping("/**") // Aplica essas regras pra TODAS as rotas da API (/** é um coringa)
            .allowedOrigins("*") // Permite que QUALQUER ORIGEM (site, app, etc) acesse a API — ideal só em ambiente de desenvolvimento!
            .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // Informa os métodos HTTP que são permitidos nas requisições
            .allowedHeaders("*"); // Permite que qualquer cabeçalho HTTP seja aceito (ex: Authorization, Content-Type, etc)
    }
}
