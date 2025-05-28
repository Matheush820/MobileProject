package com.salafacil.SalaFacilSpace.services;

import com.salafacil.SalaFacilSpace.dto.CursoRequestDTO;
import com.salafacil.SalaFacilSpace.dto.CursoResponseDTO;
import com.salafacil.SalaFacilSpace.entity.Curso;
import com.salafacil.SalaFacilSpace.mapper.CursoMapper;
import com.salafacil.SalaFacilSpace.repository.CursoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CursoService {

    private final CursoRepository cursoRepository;
    private final CursoMapper cursoMapper;

    public CursoService(CursoRepository cursoRepository, CursoMapper cursoMapper) {
        this.cursoRepository = cursoRepository;
        this.cursoMapper = cursoMapper;
    }

    public List<CursoResponseDTO> getAllCursos() {
        return cursoRepository.findAll().stream()
                .map(cursoMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    public Optional<CursoResponseDTO> getCursoById(Long id) {
        return cursoRepository.findById(id)
                .map(cursoMapper::toResponseDTO);
    }

    @Transactional
    public CursoResponseDTO createCurso(CursoRequestDTO cursoRequestDTO) {
        Curso curso = cursoMapper.toEntity(cursoRequestDTO);
        Curso salvo = cursoRepository.save(curso);
        return cursoMapper.toResponseDTO(salvo);
    }

    @Transactional
    public Optional<CursoResponseDTO> updateCurso(Long id, CursoRequestDTO cursoRequestDTO) {
        return cursoRepository.findById(id)
                .map(existingCurso -> {
                    cursoMapper.updateEntityFromDTO(cursoRequestDTO, existingCurso);
                    Curso atualizado = cursoRepository.save(existingCurso);
                    return cursoMapper.toResponseDTO(atualizado);
                });
    }

    @Transactional
    public boolean deleteCurso(Long id) {
        if (!cursoRepository.existsById(id)) {
            return false;
        }
        cursoRepository.deleteById(id);
        return true;
    }
}