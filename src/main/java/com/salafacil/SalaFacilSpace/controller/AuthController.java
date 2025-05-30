package com.salafacil.SalaFacilSpace.controller;

import com.salafacil.SalaFacilSpace.dto.LoginDTO;
import com.salafacil.SalaFacilSpace.dto.TokenDTO;
import com.salafacil.SalaFacilSpace.services.ProfessorService;
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
    private final ProfessorService professorService; 

    public AuthController(AuthenticationManager authManager, TokenService tokenService, ProfessorService professorService) {
        this.authManager = authManager;
        this.tokenService = tokenService;
        this.professorService = professorService;
    }

    @PostMapping("/login")
    public ResponseEntity<TokenDTO> login(@RequestBody @Valid LoginDTO login) {
        try {
            UsernamePasswordAuthenticationToken authToken =
                    new UsernamePasswordAuthenticationToken(login.getEmail(), login.getSenha());

            Authentication auth = authManager.authenticate(authToken);

            UserDetails userDetails = (UserDetails) auth.getPrincipal();

            String token = tokenService.gerarToken(userDetails);

            // Aqui você pega o professorId pelo email (que vem no userDetails.getUsername())
            Long professorId = professorService.buscarProfessorIdPorEmail(userDetails.getUsername());

            logger.info("Usuário {} autenticado com sucesso", login.getEmail());

            // Agora retorna token + professorId
            return ResponseEntity.ok(new TokenDTO(token, professorId));
        } catch (BadCredentialsException ex) {
            logger.warn("Falha na autenticação do usuário {}: credenciais inválidas", login.getEmail());
            return ResponseEntity.status(401).build();
        } catch (Exception ex) {
            logger.error("Erro inesperado durante autenticação do usuário {}", login.getEmail(), ex);
            return ResponseEntity.status(500).build();
        }
    }
}