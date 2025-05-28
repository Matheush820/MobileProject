package com.salafacil.SalaFacilSpace.controller;

import com.salafacil.SalaFacilSpace.dto.LoginDTO;
import com.salafacil.SalaFacilSpace.dto.TokenDTO;
import com.salafacil.SalaFacilSpace.services.TokenService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);

    private final AuthenticationManager authManager;
    private final TokenService tokenService;

    // Injeção de dependência via construtor: melhora testabilidade e clareza
    public AuthController(AuthenticationManager authManager, TokenService tokenService) {
        this.authManager = authManager;
        this.tokenService = tokenService;
    }

    /**
     * Endpoint para autenticação do usuário.
     * @param login Dados de login validados (email e senha).
     * @return Token JWT em caso de sucesso.
     */
    @PostMapping("/login")
    public ResponseEntity<TokenDTO> login(@RequestBody @Valid LoginDTO login) {
        try {
            // Cria token de autenticação do Spring Security
            UsernamePasswordAuthenticationToken authToken =
                    new UsernamePasswordAuthenticationToken(login.getEmail(), login.getSenha());

            Authentication auth = authManager.authenticate(authToken);

            UserDetails userDetails = (UserDetails) auth.getPrincipal();

            // Gera token JWT com dados do usuário autenticado
            String token = tokenService.gerarToken(userDetails);

            logger.info("Usuário {} autenticado com sucesso", login.getEmail());

            return ResponseEntity.ok(new TokenDTO(token));
        } catch (BadCredentialsException ex) {
            logger.warn("Falha na autenticação do usuário {}: credenciais inválidas", login.getEmail());
            return ResponseEntity.status(401).build(); // Unauthorized
        } catch (Exception ex) {
            logger.error("Erro inesperado durante autenticação do usuário {}", login.getEmail(), ex);
            return ResponseEntity.status(500).build(); // Internal Server Error
        }
    }
}
