package com.salafacil.SalaFacilSpace.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import com.salafacil.SalaFacilSpace.entity.Laboratorio;
import com.salafacil.SalaFacilSpace.services.LaboratorioService;

@RestController
@RequestMapping("/api/laboratorios")
public class LaboratorioController {

    @Autowired
    private LaboratorioService laboratorioService;

    @PostMapping
    public ResponseEntity<Laboratorio> createLaboratorio(@Valid @RequestBody Laboratorio laboratorio) {
        Laboratorio laboratorioCriado = laboratorioService.createLaboratorio(laboratorio);
        return ResponseEntity.ok(laboratorioCriado);
    }
}
