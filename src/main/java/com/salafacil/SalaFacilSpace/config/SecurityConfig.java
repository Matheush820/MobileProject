package com.salafacil.SalaFacilSpace.config;

import com.salafacil.SalaFacilSpace.services.MyUserDetailsService;
import com.salafacil.SalaFacilSpace.services.TokenService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.http.HttpMethod;

@Configuration // Indica que essa classe é uma configuração do Spring
@EnableWebSecurity // Habilita a segurança na aplicação web com Spring Security
public class SecurityConfig {

    private final TokenService tokenService; // Serviço responsável pela validação do token JWT
    private final MyUserDetailsService userDetailsService; // Serviço que carrega os dados do usuário do banco de dados

    // Construtor que recebe os serviços como dependências
    public SecurityConfig(TokenService tokenService, MyUserDetailsService userDetailsService) {
        this.tokenService = tokenService;
        this.userDetailsService = userDetailsService;
    }

    // Método responsável por configurar a segurança da aplicação
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable()) // Desabilita a proteção CSRF (Cross-Site Request Forgery) - necessária para APIs
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/auth/**").permitAll() // Permite o acesso sem autenticação para rotas que começam com "/auth"
                .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll() // Permite requisições OPTIONS (necessárias para CORS)
                .anyRequest().authenticated() // Exige autenticação para qualquer outra requisição
            )
            .addFilterBefore(jwtAuthFilter(), UsernamePasswordAuthenticationFilter.class); // Adiciona o filtro JWT antes do filtro de autenticação padrão do Spring

        return http.build(); // Retorna a configuração de segurança construída
    }

    // Método que cria e retorna a instância do JwtAuthFilter
    private JwtAuthFilter jwtAuthFilter() {
        return new JwtAuthFilter(tokenService, userDetailsService); // Passa os serviços necessários para o filtro
    }
}
