package com.salafacil.SalaFacilSpace.services;

import com.salafacil.SalaFacilSpace.dto.ProfessorDTO;
import com.salafacil.SalaFacilSpace.dto.RedefinirSenhaDTO;
import com.salafacil.SalaFacilSpace.entity.Professor;
import com.salafacil.SalaFacilSpace.repository.ProfessorRepository;
import com.salafacil.SalaFacilSpace.exception.ProfessorNotFoundException; // <-- IMPORTANTE!
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProfessorService {

    private final ProfessorRepository professorRepository;
    private final PasswordService passwordService;
    
    public ProfessorDTO criar(ProfessorDTO dto) {
        Professor professor = new Professor();
        professor.setNome(dto.getNome());
        professor.setEmail(dto.getEmail());
        professor.setSenha(passwordService.encodePassword(dto.getSenha()));
        Professor professorSalvo = professorRepository.save(professor);
        return toDTO(professorSalvo);
    }



    public ProfessorDTO buscarPorId(Long id) {
        Professor professor = professorRepository.findById(id)
                .orElseThrow(() -> new ProfessorNotFoundException(id)); // 👈 Atualizado aqui!
        return toDTO(professor);
    }

    public List<ProfessorDTO> listarTodos() {
        List<Professor> professores = professorRepository.findAll();
        return professores.stream()
                          .map(this::toDTO)
                          .collect(Collectors.toList());
    }

    public void resetarSenhaPorEmail(RedefinirSenhaDTO dto) {
        if (dto.getNovaSenha() == null || dto.getNovaSenha().isBlank()) {
            throw new IllegalArgumentException("A nova senha não pode estar vazia.");
        }

        Professor professor = professorRepository.findByEmail(dto.getEmail())
            .orElseThrow(() -> new ProfessorNotFoundException("Email não encontrado: " + dto.getEmail()));

        String senhaHash = passwordService.encodePassword(dto.getNovaSenha());
        professor.setSenha(senhaHash);

        professorRepository.save(professor);
    }

    
    public void deletar(Long id) {
        Professor professor = professorRepository.findById(id)
            .orElseThrow(() -> new ProfessorNotFoundException(id)); // 👈 Atualizado aqui!
        professorRepository.delete(professor);
    }

    private ProfessorDTO toDTO(Professor professor) {
        ProfessorDTO dto = new ProfessorDTO();
        dto.setId(professor.getId());
        dto.setNome(professor.getNome());
        dto.setEmail(professor.getEmail());
        return dto;
    }
}
