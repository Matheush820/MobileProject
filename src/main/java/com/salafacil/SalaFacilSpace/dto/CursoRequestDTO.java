package com.salafacil.SalaFacilSpace.dto;

import jakarta.validation.constraints.NotBlank;

public class CursoRequestDTO {

    @NotBlank(message = "O nome do curso é obrigatório.")
    private String nome;

    // Getters e Setters
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

  
}
