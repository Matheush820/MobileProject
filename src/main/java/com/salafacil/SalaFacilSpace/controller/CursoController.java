package com.salafacil.SalaFacilSpace.controller;

import com.salafacil.SalaFacilSpace.dto.CursoRequestDTO;
import com.salafacil.SalaFacilSpace.dto.CursoResponseDTO;
import com.salafacil.SalaFacilSpace.exception.ResourceNotFoundException;
import com.salafacil.SalaFacilSpace.services.CursoService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cursos")
public class CursoController {

    private final CursoService cursoService;

    public CursoController(CursoService cursoService) {
        this.cursoService = cursoService;
    }

    @GetMapping
    public ResponseEntity<List<CursoResponseDTO>> getAllCursos() {
        List<CursoResponseDTO> cursos = cursoService.getAllCursos();
        return ResponseEntity.ok(cursos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CursoResponseDTO> getCursoById(@PathVariable Long id) {
        CursoResponseDTO curso = cursoService.getCursoById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Curso não encontrado com id: " + id));
        return ResponseEntity.ok(curso);
    }

    @PostMapping
    public ResponseEntity<CursoResponseDTO> createCurso(@Valid @RequestBody CursoRequestDTO cursoRequest) {
        CursoResponseDTO cursoCriado = cursoService.createCurso(cursoRequest);
        return ResponseEntity.status(201).body(cursoCriado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CursoResponseDTO> updateCurso(@PathVariable Long id,
                                                        @Valid @RequestBody CursoRequestDTO cursoRequest) {
        CursoResponseDTO cursoAtualizado = cursoService.updateCurso(id, cursoRequest)
                .orElseThrow(() -> new ResourceNotFoundException("Curso não encontrado com id: " + id));
        return ResponseEntity.ok(cursoAtualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCurso(@PathVariable Long id) {
        if (!cursoService.deleteCurso(id)) {
            throw new ResourceNotFoundException("Curso não encontrado com id: " + id);
        }
        return ResponseEntity.noContent().build();
    }
}