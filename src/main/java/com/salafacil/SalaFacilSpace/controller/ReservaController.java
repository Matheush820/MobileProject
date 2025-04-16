package com.salafacil.SalaFacilSpace.controller;

import com.salafacil.SalaFacilSpace.dto.ReservaDTO;
import com.salafacil.SalaFacilSpace.services.ReservaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/reservas")
@RequiredArgsConstructor
public class ReservaController {
    
    private final ReservaService reservaService;

    @PostMapping
    public ResponseEntity<ReservaDTO> criarReserva(@RequestBody @Valid ReservaDTO reservaDTO) {
        ReservaDTO reservaCriada = reservaService.criarReserva(reservaDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(reservaCriada);
    }
}