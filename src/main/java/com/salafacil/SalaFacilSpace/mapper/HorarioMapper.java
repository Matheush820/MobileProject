package com.salafacil.SalaFacilSpace.mapper;

import com.salafacil.SalaFacilSpace.dto.HorarioRequest;
import com.salafacil.SalaFacilSpace.dto.HorarioResponse;
import com.salafacil.SalaFacilSpace.entity.Horario;

import java.time.DayOfWeek;

public class HorarioMapper {

    public static Horario toEntity(HorarioRequest request) {
        Horario h = new Horario();
        h.setDiaSemana(request.diaSemana().name());  // converter para String, pq na entity tá String
        h.setTurno(request.turno().toUpperCase());
        h.setHoraInicio(request.horaInicio());
        h.setHoraFim(request.horaFim());
        return h;
    }

    public static HorarioResponse toResponse(Horario h) {
        return new HorarioResponse(
            h.getId(),
            DayOfWeek.valueOf(h.getDiaSemana()), // converter String para DayOfWeek
            h.getTurno(),
            h.getHoraInicio(),
            h.getHoraFim()
        );
    }
}
