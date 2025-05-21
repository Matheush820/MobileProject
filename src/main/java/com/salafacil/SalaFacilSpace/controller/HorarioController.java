package com.salafacil.SalaFacilSpace.controller;

import com.salafacil.SalaFacilSpace.entity.Horario;
import com.salafacil.SalaFacilSpace.services.HorarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/horarios")
public class HorarioController {

    @Autowired
    private HorarioService horarioService;

    @PostMapping
    public ResponseEntity<Horario> createHorario(@Valid @RequestBody Horario horario) {
        Horario horarioCriado = horarioService.createHorario(horario);
        return ResponseEntity.ok(horarioCriado);
    }

    @GetMapping
    public ResponseEntity<List<Horario>> getAllHorarios() {
        return ResponseEntity.ok(horarioService.getAllHorarios());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Horario> getHorarioById(@PathVariable Long id) {
        Optional<Horario> horario = horarioService.getHorarioById(id);
        return horario.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Horario> updateHorario(@PathVariable Long id, @Valid @RequestBody Horario horario) {
        Optional<Horario> atualizado = horarioService.updateHorario(id, horario);
        return atualizado.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteHorario(@PathVariable Long id) {
        boolean deletado = horarioService.deleteHorario(id);
        return deletado ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }

    // 🔥 Endpoint para buscar horários disponíveis por data e turno
    @GetMapping("/disponiveis")
    public ResponseEntity<List<Horario>> listarHorariosPorDataEPorTurno(
            @RequestParam("data") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate data,
            @RequestParam("turno") String turno) {

        List<Horario> horarios = horarioService.buscarHorariosPorDataEPorTurno(data, turno);

        if (horarios.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(horarios);
    }
}
