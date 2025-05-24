package com.salafacil.SalaFacilSpace.services;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Objects;

/**
 * Serviço responsável por operações com senha, como geração e validação de hash.
 * 
 * Implementa boa prática de injeção de dependência e validações básicas.
 * Mantém responsabilidade única e fácil testabilidade.
 */
@Service
public class PasswordService {

    private final BCryptPasswordEncoder passwordEncoder;

    // Injeção de dependência pelo construtor, padrão Spring.
    public PasswordService(BCryptPasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * Cria um hash seguro da senha usando BCrypt.
     * @param password senha em texto puro.
     * @return hash gerado.
     * @throws IllegalArgumentException se a senha for nula ou vazia.
     */
    public String encodePassword(String password) {
        if (password == null || password.isEmpty()) {
            throw new IllegalArgumentException("Senha não pode ser nula ou vazia");
        }
        return passwordEncoder.encode(password);
    }

    /**
     * Verifica se a senha em texto puro corresponde ao hash armazenado.
     * @param rawPassword senha digitada pelo usuário.
     * @param hashedPassword hash armazenado.
     * @return true se confere, false caso contrário.
     * @throws IllegalArgumentException se algum parâmetro for nulo, para evitar mascarar erros upstream.
     */
    public boolean matches(String rawPassword, String hashedPassword) {
        if (Objects.isNull(rawPassword) || Objects.isNull(hashedPassword)) {
            throw new IllegalArgumentException("rawPassword e hashedPassword não podem ser nulos");
        }
        return passwordEncoder.matches(rawPassword, hashedPassword);
    }
}
