package com.salafacil.SalaFacilSpace.mapper;

import com.salafacil.SalaFacilSpace.dto.CursoRequestDTO;
import com.salafacil.SalaFacilSpace.dto.CursoResponseDTO;
import com.salafacil.SalaFacilSpace.entity.Curso;
import org.springframework.stereotype.Component;

@Component
public class CursoMapper {

    public CursoResponseDTO toResponseDTO(Curso curso) {
        if (curso == null) return null;
        return new CursoResponseDTO(
            curso.getId(),
            curso.getNome()
        );
    }

    public Curso toEntity(CursoRequestDTO dto) {
        if (dto == null) return null;
        Curso curso = new Curso();
        curso.setNome(dto.getNome());
        return curso;
    }

    public void updateEntityFromDTO(CursoRequestDTO dto, Curso curso) {
        if (dto == null || curso == null) return;
        curso.setNome(dto.getNome());
    }
}
