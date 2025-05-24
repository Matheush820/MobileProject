package com.salafacil.SalaFacilSpace.mapper;

import com.salafacil.SalaFacilSpace.dto.ProfessorRequestDTO;
import com.salafacil.SalaFacilSpace.dto.ProfessorResponseDTO;
import com.salafacil.SalaFacilSpace.entity.Professor;
import org.springframework.stereotype.Component;

@Component
public class ProfessorMapper {

    public Professor toEntity(ProfessorRequestDTO dto) {
        if (dto == null) return null;

        Professor professor = new Professor();
        professor.setNome(dto.getNome());
        professor.setEmail(dto.getEmail());
        // A senha é tratada no service, não aqui (boa prática)
        return professor;
    }

    public ProfessorResponseDTO toResponseDTO(Professor professor) {
        if (professor == null) return null;

        return ProfessorResponseDTO.builder()
                .id(professor.getId())
                .nome(professor.getNome())
                .email(professor.getEmail())
                .build();
    }
}
