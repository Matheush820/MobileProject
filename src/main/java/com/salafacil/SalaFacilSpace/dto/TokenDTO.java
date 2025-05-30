package com.salafacil.SalaFacilSpace.dto;

public class TokenDTO {
    private String token;
    private Long professorId;  // adiciona o professorId

    // Construtor com token e professorId
    public TokenDTO(String token, Long professorId) {
        this.token = token;
        this.professorId = professorId;
    }

    // Construtor só com token (opcional, caso precise compatibilidade)
    public TokenDTO(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public Long getProfessorId() {
        return professorId;
    }
}