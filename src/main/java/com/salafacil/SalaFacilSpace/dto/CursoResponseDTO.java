package com.salafacil.SalaFacilSpace.dto;

public class CursoResponseDTO {

    private Long id;
    private String nome;

    // CONSTRUTOR que está faltando!
    public CursoResponseDTO(Long id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    // GETTERS (sem setters se quiser deixar imutável)
    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }
}
