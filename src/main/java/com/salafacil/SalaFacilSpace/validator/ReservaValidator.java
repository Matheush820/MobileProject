package com.salafacil.SalaFacilSpace.validator;

import com.salafacil.SalaFacilSpace.dto.ReservaDTO;
import com.salafacil.SalaFacilSpace.exception.ReservaConflictException;
import com.salafacil.SalaFacilSpace.repository.ReservaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ReservaValidator {

    private final ReservaRepository reservaRepository;

    public void validarConflito(ReservaDTO reservaDTO) {
        boolean existeConflito = reservaRepository.existsByDataAndLaboratorioIdAndHorarioId(
                reservaDTO.getData(),
                reservaDTO.getLaboratorioId(),
                reservaDTO.getHorarioId()
        );

        if (existeConflito) {
            throw new ReservaConflictException("Já existe uma reserva para este laboratório no horário selecionado.");
        }
    }
}
