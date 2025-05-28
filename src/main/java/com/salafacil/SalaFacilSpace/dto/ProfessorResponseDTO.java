package com.salafacil.SalaFacilSpace.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

// DTO bonitão, enxuto e profissional.
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProfessorResponseDTO {
    private Long id;
    private String nome;
    private String email;
}
