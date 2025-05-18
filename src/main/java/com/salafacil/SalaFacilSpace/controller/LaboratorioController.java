package com.salafacil.SalaFacilSpace.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import com.salafacil.SalaFacilSpace.entity.Laboratorio;
import com.salafacil.SalaFacilSpace.services.LaboratorioService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

import java.util.List;
import java.util.Optional;

@SecurityRequirement(name = "bearerAuth")
@RestController
@RequestMapping("/api/laboratorios")
public class LaboratorioController {

    @Autowired
    private LaboratorioService laboratorioService;

    // Método GET para listar todos os laboratórios
    @GetMapping
    public ResponseEntity<List<Laboratorio>> getAllLaboratorios() {
        List<Laboratorio> laboratorios = laboratorioService.getAllLaboratorios();
        return ResponseEntity.ok(laboratorios);
    }

    // Método GET para buscar um laboratório por ID
    @GetMapping("/{id}")
    public ResponseEntity<Laboratorio> getLaboratorioById(@PathVariable Long id) {
        Optional<Laboratorio> laboratorio = laboratorioService.getLaboratorioById(id);
        if (laboratorio.isPresent()) {
            return ResponseEntity.ok(laboratorio.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Método POST para criar um novo laboratório
    @PostMapping
    public ResponseEntity<Laboratorio> createLaboratorio(@Valid @RequestBody Laboratorio laboratorio) {
        Laboratorio laboratorioCriado = laboratorioService.createLaboratorio(laboratorio);
        return ResponseEntity.ok(laboratorioCriado);
    }

    // Método PUT para atualizar um laboratório existente
    @PutMapping("/{id}")
    public ResponseEntity<Laboratorio> updateLaboratorio(@PathVariable Long id, @Valid @RequestBody Laboratorio laboratorio) {
        Optional<Laboratorio> laboratorioExistente = laboratorioService.getLaboratorioById(id);
        if (laboratorioExistente.isPresent()) {
            laboratorio.setId(id);  // Certifique-se de que o ID correto será usado para a atualização
            Laboratorio laboratorioAtualizado = laboratorioService.updateLaboratorio(laboratorio);
            return ResponseEntity.ok(laboratorioAtualizado);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Método DELETE para excluir um laboratório
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLaboratorio(@PathVariable Long id) {
        Optional<Laboratorio> laboratorioExistente = laboratorioService.getLaboratorioById(id);
        if (laboratorioExistente.isPresent()) {
            laboratorioService.deleteLaboratorio(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
