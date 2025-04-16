package com.salafacil.SalaFacilSpace.services;

import com.salafacil.SalaFacilSpace.dto.ProfessorDTO;
import com.salafacil.SalaFacilSpace.entity.Professor;
import com.salafacil.SalaFacilSpace.repository.ProfessorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProfessorService {

    private final ProfessorRepository professorRepository;

    public ProfessorDTO criar(ProfessorDTO dto, String senhaPadrao) {
        Professor professor = new Professor();
        professor.setNome(dto.getNome());
        professor.setEmail(dto.getEmail());
        professor.setMatricula(dto.getMatricula());
        professor.setSenha(senhaPadrao); // Em produção, usar criptografia
        
        Professor professorSalvo = professorRepository.save(professor);
        return toDTO(professorSalvo);
    }

    public ProfessorDTO buscarPorId(Long id) {
        Professor professor = professorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Professor não encontrado"));
        return toDTO(professor);
    }

    private ProfessorDTO toDTO(Professor professor) {
        ProfessorDTO dto = new ProfessorDTO();
        dto.setId(professor.getId());
        dto.setNome(professor.getNome());
        dto.setEmail(professor.getEmail());
        dto.setMatricula(professor.getMatricula());
        return dto;
    }
}