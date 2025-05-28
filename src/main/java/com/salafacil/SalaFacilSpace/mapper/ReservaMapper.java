// ReservaMapper.java
package com.salafacil.SalaFacilSpace.mapper;

import com.salafacil.SalaFacilSpace.dto.ReservaDTO;
import com.salafacil.SalaFacilSpace.entity.Reserva;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ReservaMapper {

    public ReservaDTO toDTO(Reserva reserva) {
        return ReservaDTO.builder()
                .data(reserva.getData())
                .professorId(reserva.getProfessor().getId())
                .cursoId(reserva.getCurso().getId())
                .laboratorioId(reserva.getLaboratorio().getId())
                .horarioId(reserva.getHorario().getId())
                .categoriaId(reserva.getCategoria().getId())
                .build();
    }
}
