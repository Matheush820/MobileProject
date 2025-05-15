package com.salafacil.SalaFacilSpace.config;

import jakarta.servlet.http.HttpServletResponse;
import com.salafacil.SalaFacilSpace.services.MyUserDetailsService;
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

        log.info("Interceptando path: {}", path);

    	
        if (path.startsWith("/auth")
        	    || path.startsWith("/swagger-ui")
        	    || path.equals("/swagger-ui.html")
        	    || path.startsWith("/swagger-resources")
        	    || path.equals("/v3/api-docs") 
        	    || path.startsWith("/v3/api-docs/")
        	    || path.startsWith("/webjars")
        	    || path.equals("/")) {
        	    
        	    filterChain.doFilter(request, response);
        	    return;
        	}

       

        String token = obterToken(request);

        if (token != null && tokenService.validarToken(token)) {
            try {
                String username = tokenService.obterUsernameDoToken(token);
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
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                response.getWriter().write("Token inválido ou erro na autenticação");
                return;
            }
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
