// 🔥 Service bonitão, sem peso morto
package com.salafacil.SalaFacilSpace.services;

import com.salafacil.SalaFacilSpace.dto.ProfessorRequestDTO;
import com.salafacil.SalaFacilSpace.dto.ProfessorResponseDTO;
import com.salafacil.SalaFacilSpace.entity.Professor;
import com.salafacil.SalaFacilSpace.exception.ProfessorNotFoundException;
import com.salafacil.SalaFacilSpace.mapper.ProfessorMapper;
import com.salafacil.SalaFacilSpace.repository.ProfessorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProfessorService {

    private final ProfessorRepository professorRepository;
    private final PasswordService passwordService;
    private final ProfessorMapper mapper;

    @Value("${app.senha-padrao}")
    private String senhaPadrao;

    public ProfessorResponseDTO criar(ProfessorRequestDTO dto) {
        verificarEmailDisponivel(dto.getEmail());

        Professor professor = mapper.toEntity(dto);
        professor.setSenha(passwordService.encodePassword(senhaPadrao));

        Professor salvo = professorRepository.save(professor);
        return mapper.toResponseDTO(salvo);
    }

    public ProfessorResponseDTO buscarPorId(Long id) {
        Professor professor = buscarOuFalhar(id);
        return mapper.toResponseDTO(professor);
    }

    public List<ProfessorResponseDTO> listarTodos() {
        return professorRepository.findAll().stream()
                .map(mapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    public ProfessorResponseDTO atualizar(Long id, ProfessorRequestDTO dto) {
        Professor professor = buscarOuFalhar(id);

        if (!professor.getEmail().equals(dto.getEmail())) {
            verificarEmailDisponivel(dto.getEmail());
        }

        professor.setNome(dto.getNome());
        professor.setEmail(dto.getEmail());

        if (dto.getSenha() != null && !dto.getSenha().isEmpty()) {
            professor.setSenha(passwordService.encodePassword(dto.getSenha()));
        }

        Professor atualizado = professorRepository.save(professor);
        return mapper.toResponseDTO(atualizado);
    }

    public void deletar(Long id) {
        Professor professor = buscarOuFalhar(id);
        professorRepository.delete(professor);
    }

    private Professor buscarOuFalhar(Long id) {
        return professorRepository.findById(id)
                .orElseThrow(() -> new ProfessorNotFoundException(id));
    }

    private void verificarEmailDisponivel(String email) {
        if (professorRepository.findByEmail(email).isPresent()) {
            throw new IllegalArgumentException("Email já está em uso.");
        }
    }
}
