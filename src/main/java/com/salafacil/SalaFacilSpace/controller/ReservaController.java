package com.salafacil.SalaFacilSpace.controller;

import com.salafacil.SalaFacilSpace.dto.ReservaDTO;
import com.salafacil.SalaFacilSpace.dto.ReservaResponseDTO;
import com.salafacil.SalaFacilSpace.entity.Reserva;
import com.salafacil.SalaFacilSpace.repository.ReservaRepository;
import com.salafacil.SalaFacilSpace.services.ReservaService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@SecurityRequirement(name = "bearerAuth")
@RestController
@RequestMapping("/api/reservas")
@RequiredArgsConstructor
public class ReservaController {

    private final ReservaService reservaService;
    private final ReservaRepository reservaRepository;

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

    // Endpoint para listar todas as reservas (com nome do professor no DTO de resposta)
    @GetMapping
    public ResponseEntity<List<ReservaResponseDTO>> listarReservas() {
        List<Reserva> reservas = reservaRepository.findAll();
        List<ReservaResponseDTO> dtos = reservas.stream()
                .map(ReservaResponseDTO::new)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    // 🚀 Endpoint para listar reservas de um professor específico
    @GetMapping("/professor/{professorId}")
    public ResponseEntity<List<ReservaResponseDTO>> listarReservasPorProfessor(@PathVariable Long professorId) {
        List<Reserva> reservas = reservaRepository.findByProfessorId(professorId);
        List<ReservaResponseDTO> dtos = reservas.stream()
                .map(ReservaResponseDTO::new)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
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
