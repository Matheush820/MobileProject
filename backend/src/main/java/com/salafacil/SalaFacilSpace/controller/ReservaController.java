package com.salafacil.SalaFacilSpace.controller;

import com.salafacil.SalaFacilSpace.dto.ReservaDTO;
import com.salafacil.SalaFacilSpace.services.ReservaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reservas")
@RequiredArgsConstructor
public class ReservaController {

    private final ReservaService reservaService;

    // Endpoint para criar uma nova reserva
    @PostMapping
    public ResponseEntity<ReservaDTO> criarReserva(@RequestBody @Valid ReservaDTO reservaDTO) {
        ReservaDTO reservaCriada = reservaService.criarReserva(reservaDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(reservaCriada);
    }

    // Endpoint para obter uma reserva por ID
    @GetMapping("/{id}")
    public ResponseEntity<ReservaDTO> obterReserva(@PathVariable Long id) {
        ReservaDTO reserva = reservaService.obterReserva(id);
        return ResponseEntity.ok(reserva);
    }

    // ✅ NOVO: Endpoint para listar todas as reservas
    @GetMapping
    public ResponseEntity<List<ReservaDTO>> listarTodasReservas() {
        List<ReservaDTO> reservas = reservaService.listarTodasReservas();
        return ResponseEntity.ok(reservas);
    }

    // Endpoint para atualizar uma reserva existente
    @PutMapping("/{id}")
    public ResponseEntity<ReservaDTO> atualizarReserva(@PathVariable Long id, @RequestBody @Valid ReservaDTO reservaDTO) {
        ReservaDTO reservaAtualizada = reservaService.atualizarReserva(id, reservaDTO);
        return ResponseEntity.ok(reservaAtualizada);
    }

    // Endpoint para deletar uma reserva
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarReserva(@PathVariable Long id) {
        reservaService.deletarReserva(id);
        return ResponseEntity.noContent().build();
    }
}
