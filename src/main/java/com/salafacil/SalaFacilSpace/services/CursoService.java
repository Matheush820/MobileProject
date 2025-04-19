package com.salafacil.SalaFacilSpace.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.salafacil.SalaFacilSpace.entity.Curso;
import com.salafacil.SalaFacilSpace.repository.CursoRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CursoService {

    @Autowired
    private CursoRepository cursoRepository;

    // Lista todos os cursos
    public List<Curso> getAllCursos() {
        return cursoRepository.findAll();
    }

    // Obtém um curso pelo ID
    public Curso getCursoById(Long id) {
        Optional<Curso> curso = cursoRepository.findById(id);
        return curso.orElse(null);
    }

    // Cria um novo curso
    public Curso createCurso(Curso curso) {
        return cursoRepository.save(curso);
    }

    // Atualiza um curso existente
    public Curso updateCurso(Long id, Curso curso) {
        if (cursoRepository.existsById(id)) {
            curso.setId(id);
            return cursoRepository.save(curso);
        }
        return null;
    }

    // Deleta um curso
    public void deleteCurso(Long id) {
        cursoRepository.deleteById(id);
    }
}
