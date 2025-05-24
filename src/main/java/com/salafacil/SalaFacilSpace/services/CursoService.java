package com.salafacil.SalaFacilSpace.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.salafacil.SalaFacilSpace.entity.Curso;
import com.salafacil.SalaFacilSpace.entity.Horario;
import com.salafacil.SalaFacilSpace.repository.CursoRepository;
import com.salafacil.SalaFacilSpace.repository.HorarioRepository;

import jakarta.transaction.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class CursoService {

    @Autowired
    private CursoRepository cursoRepository;
    
    @Autowired
    private HorarioRepository horarioRepository;

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
    
    @Transactional
    public Curso adicionarHorariosAoCurso(Long cursoId, List<Long> horariosIds) {
        Optional<Curso> cursoOpt = cursoRepository.findById(cursoId);
        if (cursoOpt.isEmpty()) return null;

        Curso curso = cursoOpt.get();
        List<Horario> horarios = horarioRepository.findAllById(horariosIds);

        if (curso.getHorarios() == null) {
            curso.setHorarios(new HashSet<>());
        }

        curso.getHorarios().addAll(horarios);
        return cursoRepository.save(curso);
    }

    public Set<Horario> listarHorariosDoCurso(Long cursoId) {
        Optional<Curso> cursoOpt = cursoRepository.findById(cursoId);
        return cursoOpt.map(Curso::getHorarios).orElse(Set.of());
    }
}
