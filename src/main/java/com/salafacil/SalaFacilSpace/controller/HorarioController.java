package com.salafacil.SalaFacilSpace.controller;

import com.salafacil.SalaFacilSpace.dto.HorarioRequest;
import com.salafacil.SalaFacilSpace.dto.HorarioResponse;
import com.salafacil.SalaFacilSpace.services.HorarioService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/horarios")
public class HorarioController {

    @Autowired
    private HorarioService horarioService;

    @PostMapping
    public ResponseEntity<HorarioResponse> createHorario(@Valid @RequestBody HorarioRequest request) {
        HorarioResponse horarioCriado = horarioService.createHorario(request);
        return ResponseEntity.ok(horarioCriado);
    }

    @GetMapping
    public ResponseEntity<List<HorarioResponse>> getAllHorarios() {
        List<HorarioResponse> horarios = horarioService.getAllHorarios();
        return ResponseEntity.ok(horarios);
    }

    @GetMapping("/{id}")
    public ResponseEntity<HorarioResponse> getHorarioById(@PathVariable Long id) {
        HorarioResponse horario = horarioService.getHorarioById(id);
        return ResponseEntity.ok(horario);
    }

    @PutMapping("/{id}")
    public ResponseEntity<HorarioResponse> updateHorario(@PathVariable Long id, @Valid @RequestBody HorarioRequest request) {
        HorarioResponse atualizado = horarioService.updateHorario(id, request);
        return ResponseEntity.ok(atualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteHorario(@PathVariable Long id) {
        horarioService.deleteHorario(id);
        return ResponseEntity.noContent().build();
    }

    // Endpoint para buscar horários disponíveis por data e turno
    @GetMapping("/disponiveis")
    public ResponseEntity<List<HorarioResponse>> listarHorariosPorDataEPorTurno(
            @RequestParam("data") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate data,
            @RequestParam("turno") String turno) {

        List<HorarioResponse> horarios = horarioService.buscarPorDataEPorTurno(data, turno);

        if (horarios.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(horarios);
    }
}