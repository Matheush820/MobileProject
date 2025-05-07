package com.salafacil.SalaFacilSpace.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.salafacil.SalaFacilSpace.dto.LoginDTO;
import com.salafacil.SalaFacilSpace.dto.TokenDTO;
import com.salafacil.SalaFacilSpace.services.TokenService;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/auth")
public class AuthController {

	@Autowired
    private AuthenticationManager authManager;

    @Autowired
    private TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDTO login) {
        UsernamePasswordAuthenticationToken dadosLogin =
            new UsernamePasswordAuthenticationToken(login.getEmail(), login.getSenha());

        Authentication auth = authManager.authenticate(dadosLogin);

        String token = tokenService.gerarToken((UserDetails) auth.getPrincipal());

        return ResponseEntity.ok(new TokenDTO(token));
    }
}
