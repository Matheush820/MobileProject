package com.salafacil.SalaFacilSpace.controller;
import com.salafacil.SalaFacilSpace.entity.Horario;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.salafacil.SalaFacilSpace.entity.Curso;
import com.salafacil.SalaFacilSpace.services.CursoService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

import java.util.List;
import java.util.Set;

@SecurityRequirement(name = "bearerAuth")
@RestController
@RequestMapping("/api/cursos")
public class CursoController {

    @Autowired
    private CursoService cursoService;

    // Endpoint para listar todos os cursos
    @GetMapping
    public List<Curso> getAllCursos() {
        return cursoService.getAllCursos();
    }

    // Endpoint para obter um curso por ID
    @GetMapping("/{id}")
    public Curso getCursoById(@PathVariable Long id) {
        return cursoService.getCursoById(id);
    }

    // Endpoint para criar um novo curso
    @PostMapping
    public Curso createCurso(@RequestBody Curso curso) {
        return cursoService.createCurso(curso);
    }

    // Endpoint para atualizar um curso
    @PutMapping("/{id}")
    public Curso updateCurso(@PathVariable Long id, @RequestBody Curso curso) {
        return cursoService.updateCurso(id, curso);
    }

    // Endpoint para deletar um curso
    @DeleteMapping("/{id}")
    public void deleteCurso(@PathVariable Long id) {
        cursoService.deleteCurso(id);
    }
    
    @PostMapping("/{cursoId}/horarios")
    public ResponseEntity<?> adicionarHorariosAoCurso(
            @PathVariable Long cursoId,
            @RequestBody List<Long> horariosIds) {
        var curso = cursoService.adicionarHorariosAoCurso(cursoId, horariosIds);
        return curso != null ? ResponseEntity.ok(curso) : ResponseEntity.notFound().build();
    }

    @GetMapping("/{cursoId}/horarios")
    public ResponseEntity<Set<Horario>> listarHorariosDoCurso(@PathVariable Long cursoId) {
        var horarios = cursoService.listarHorariosDoCurso(cursoId);
        return ResponseEntity.ok(horarios);
    }
}
