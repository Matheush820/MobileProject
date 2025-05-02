package com.salafacil.SalaFacilSpace.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "laboratorio")
public class Laboratorio {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Nome do laboratório é obrigatório")
    @Size(max = 100, message = "Nome do laboratório deve ter no máximo 100 caracteres")
    private String nome;

    @NotBlank(message = "Localização é obrigatória")
    @Size(max = 200, message = "Localização deve ter no máximo 200 caracteres")
    private String localizacao;

    @NotBlank(message = "Bloco é obrigatório")
    @Size(max = 10, message = "Bloco deve ter no máximo 10 caracteres")
    private String bloco;

    @NotBlank(message = "Andar é obrigatório")
    @Size(max = 10, message = "Andar deve ter no máximo 10 caracteres")
    private String andar;

    @NotBlank(message = "Número é obrigatório")
    @Size(max = 10, message = "Número deve ter no máximo 10 caracteres")
    private String numero;
}