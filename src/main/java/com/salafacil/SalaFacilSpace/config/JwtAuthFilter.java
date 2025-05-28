package com.salafacil.SalaFacilSpace.config;

import jakarta.servlet.http.HttpServletResponse;
import com.salafacil.SalaFacilSpace.services.MyUserDetailsService;
import com.salafacil.SalaFacilSpace.services.ProfessorUserDetailsService;
import com.salafacil.SalaFacilSpace.services.TokenService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;
import java.io.IOException;

public class JwtAuthFilter extends OncePerRequestFilter {

    // Logger para registrar mensagens importantes (avisos, erros, etc.)
    private static final Logger log = LoggerFactory.getLogger(JwtAuthFilter.class); 

    // Serviços necessários para validação do token e recuperação dos dados do usuário
    private final TokenService tokenService;
    private final MyUserDetailsService userDetailsService;

    // Construtor para injetar as dependências necessárias
    public JwtAuthFilter(TokenService tokenService, MyUserDetailsService userDetailsService) {
        this.tokenService = tokenService;
        this.userDetailsService = userDetailsService;
    }

    // Método principal chamado em cada requisição para aplicar o filtro JWT
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String path = request.getRequestURI();
        System.out.println("Interceptando requisição: " + path);

        // Ignorar rotas públicas
        if (path.startsWith("/auth")
                || path.startsWith("/swagger-ui")
                || path.equals("/swagger-ui.html")
                || path.equals("/auth/login")
                || path.startsWith("/swagger-resources")
                || path.equals("/v3/api-docs")
                || path.startsWith("/v3/api-docs/")
                || path.startsWith("/webjars")
                || path.equals("/")
                || (path.equals("/api/professores") && request.getMethod().equals("POST"))
                || (path.equals("/api/professores/resetar-senha") && request.getMethod().equals("PUT"))
        ) {
            System.out.println("Rota pública, liberando sem filtro JWT");
            filterChain.doFilter(request, response);
            return;
        }

        String token = obterToken(request);

        if (token != null && tokenService.validarToken(token)) {
            try {
            	log.info("Token recebido: {}", token);
                String username = tokenService.obterUsernameDoToken(token);
                log.info("Usuário extraído do token: {}", username);

                var usuario = userDetailsService.loadUserByUsername(username);

                var authentication = new UsernamePasswordAuthenticationToken(
                        usuario,
                        null,
                        usuario.getAuthorities()
                );

                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authentication);

            } catch (Exception e) {
                log.warn("Falha ao processar token: Token inválido ou erro ao validar o token", e);
                // Responde 401, mas cuidado para não quebrar o fluxo
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                response.getWriter().write("Token inválido ou erro na autenticação");
                return;
            }
        } else if (token == null) {
            // Não autenticado, deixa o Spring Security lidar com a resposta de falta de autenticação
            // Ou seja, NÃO bloqueia aqui com status e retorno manual
            // Apenas não autentica e deixa seguir
            System.out.println("Token não fornecido, não autenticado");
        }

        filterChain.doFilter(request, response);
    }

    // Método auxiliar para extrair o token do cabeçalho Authorization
    private String obterToken(HttpServletRequest request) {
        String authorizationHeader = request.getHeader("Authorization");

        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            return authorizationHeader.substring(7); // Remove "Bearer " e retorna apenas o token
        }

        return null; // Se não houver token ou estiver em formato incorreto
    }
}
