package com.salafacil.SalaFacilSpace.controllers;

import com.salafacil.SalaFacilSpace.dto.LoginDTO;
import com.salafacil.SalaFacilSpace.dto.TokenDTO;
import com.salafacil.SalaFacilSpace.services.TokenService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthenticationManager authManager;
    private final TokenService tokenService;

    public AuthController(AuthenticationManager authManager, TokenService tokenService) {
        this.authManager = authManager;
        this.tokenService = tokenService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDTO login) {
        try {
            UsernamePasswordAuthenticationToken credentials =
                new UsernamePasswordAuthenticationToken(login.getEmail(), login.getSenha());

            Authentication authentication = authManager.authenticate(credentials);
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();

            String token = tokenService.gerarToken(userDetails);

            return ResponseEntity.ok(new TokenDTO(token));

        } catch (BadCredentialsException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciais inválidas");
        } catch (AuthenticationException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Erro ao autenticar usuário");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro interno");
        }
    }
}
