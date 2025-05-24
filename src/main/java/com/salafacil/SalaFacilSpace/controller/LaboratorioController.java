package com.salafacil.SalaFacilSpace.controller;

import com.salafacil.SalaFacilSpace.entity.Laboratorio;
import com.salafacil.SalaFacilSpace.services.LaboratorioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/laboratorios")
public class LaboratorioController {

    @Autowired
    private LaboratorioService laboratorioService;

    @GetMapping
    public ResponseEntity<List<Laboratorio>> getAll() {
        return ResponseEntity.ok(laboratorioService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Laboratorio> getById(@PathVariable Long id) {
        return ResponseEntity.ok(laboratorioService.getById(id));
    }

    @PostMapping
    public ResponseEntity<Laboratorio> create(@Valid @RequestBody Laboratorio laboratorio) {
        return ResponseEntity.ok(laboratorioService.create(laboratorio));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Laboratorio> update(@PathVariable Long id, @Valid @RequestBody Laboratorio laboratorio) {
        return ResponseEntity.ok(laboratorioService.update(id, laboratorio));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        laboratorioService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
