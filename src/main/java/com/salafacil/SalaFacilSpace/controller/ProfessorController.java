package com.salafacil.SalaFacilSpace.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.salafacil.SalaFacilSpace.dto.ProfessorDTO;
import com.salafacil.SalaFacilSpace.dto.RedefinirSenhaDTO;
import com.salafacil.SalaFacilSpace.services.ProfessorService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@SecurityRequirement(name = "bearerAuth")
@RestController
@RequestMapping("/api/professores")
@RequiredArgsConstructor
public class ProfessorController {

    private final ProfessorService professorService;

    @Operation(security = @SecurityRequirement(name = ""))
    @PostMapping
    public ResponseEntity<ProfessorDTO> criar(@RequestBody @Valid ProfessorDTO dto) {
        return ResponseEntity.ok(professorService.criar(dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProfessorDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(professorService.buscarPorId(id));
    }

    @GetMapping
    public ResponseEntity<List<ProfessorDTO>> listarTodos() {
        return ResponseEntity.ok(professorService.listarTodos());
    }

    @PutMapping("/resetar-senha")
    public ResponseEntity<Void> resetarSenha(@RequestBody @Valid RedefinirSenhaDTO dto) {
        professorService.resetarSenhaPorEmail(dto);
        return ResponseEntity.ok().build();
    }



    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        professorService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
