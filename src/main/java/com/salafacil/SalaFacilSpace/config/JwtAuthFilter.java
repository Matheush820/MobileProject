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

    // Instanciação do Logger para registrar eventos ou erros durante o processo de autenticação
    private static final Logger log = LoggerFactory.getLogger(JwtAuthFilter.class); 

    // Serviços necessários para validar o token e carregar os dados do usuário
    private final TokenService tokenService;
    private final MyUserDetailsService userDetailsService;

    // Construtor que recebe os serviços como dependências e os inicializa
    public JwtAuthFilter(TokenService tokenService, MyUserDetailsService userDetailsService) {
        this.tokenService = tokenService;
        this.userDetailsService = userDetailsService;
    }

    // Este método é chamado toda vez que uma requisição passa pelo filtro
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        // Tenta obter o token JWT do cabeçalho da requisição
        String token = obterToken(request);

        // Se o token não for nulo e for válido, procede com a autenticação
        if (token != null && tokenService.validarToken(token)) {
            try {
                // Obtém o nome de usuário do token JWT
                String username = tokenService.obterUsernameDoToken(token);

                // Carrega os dados do usuário utilizando o nome de usuário
                var usuario = userDetailsService.loadUserByUsername(username);

                // Cria um objeto de autenticação com as informações do usuário e suas permissões
                var authentication = new UsernamePasswordAuthenticationToken(
                        usuario,  // O usuário carregado
                        null,     // Não é necessário passar a senha, pois estamos autenticando via token
                        usuario.getAuthorities()  // As permissões (roles) do usuário
                );

                // Adiciona detalhes sobre a requisição (como o IP) à autenticação
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                // Define a autenticação no contexto de segurança do Spring, permitindo que o sistema saiba que o usuário está autenticado
                SecurityContextHolder.getContext().setAuthentication(authentication);

            } catch (Exception e) {
                // Caso haja algum erro ao validar o token ou ao carregar o usuário, loga o erro e retorna status 401 (não autorizado)
                log.warn("Falha ao processar token: Token inválido ou erro ao validar o token", e);
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED); // Responde com erro de não autorizado
                response.getWriter().write("Token inválido ou erro na autenticação"); // Mensagem para o cliente
                return; // Impede que o restante da cadeia de filtros seja executada
            }
        }

        // Continua a execução do filtro para a próxima etapa da requisição, caso o token seja válido ou não haja token
        filterChain.doFilter(request, response);
    }

    // Método que busca o token JWT no cabeçalho da requisição
    private String obterToken(HttpServletRequest request) {
        // Obtém o valor do cabeçalho "Authorization" da requisição
        String authorizationHeader = request.getHeader("Authorization");

        // Verifica se o cabeçalho está presente e começa com "Bearer ", que é o formato esperado para o token
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            return authorizationHeader.substring(7); // Retorna o token, removendo a parte "Bearer "
        }

        // Retorna null se o token não estiver presente ou no formato esperado
        return null;
    }
}
