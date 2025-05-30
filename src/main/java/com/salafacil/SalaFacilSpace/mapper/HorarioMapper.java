package com.salafacil.SalaFacilSpace.mapper;

import com.salafacil.SalaFacilSpace.dto.HorarioRequest;
import com.salafacil.SalaFacilSpace.dto.HorarioResponse;
import com.salafacil.SalaFacilSpace.entity.Horario;

import java.time.DayOfWeek;
import java.util.Map;

public class HorarioMapper {

    private static final Map<String, DayOfWeek> DIA_SEMANA_MAP = Map.of(
        "SEGUNDA-FEIRA", DayOfWeek.MONDAY,
        "TERÇA-FEIRA", DayOfWeek.TUESDAY,
        "QUARTA-FEIRA", DayOfWeek.WEDNESDAY,
        "QUINTA-FEIRA", DayOfWeek.THURSDAY,
        "SEXTA-FEIRA", DayOfWeek.FRIDAY,
        "SÁBADO", DayOfWeek.SATURDAY,
        "DOMINGO", DayOfWeek.SUNDAY
    );

    public static Horario toEntity(HorarioRequest request) {
        Horario h = new Horario();
        h.setDiaSemana(request.diaSemana().name()); // Continua salvando como String
        h.setTurno(request.turno().toUpperCase());
        h.setHoraInicio(request.horaInicio());
        h.setHoraFim(request.horaFim());
        return h;
    }

    public static HorarioResponse toResponse(Horario h) {
        return new HorarioResponse(
            h.getId(),
            parseDiaSemana(h.getDiaSemana()),
            h.getTurno(),
            h.getHoraInicio(),
            h.getHoraFim()
        );
    }

    private static DayOfWeek parseDiaSemana(String dia) {
        if (dia == null) {
            throw new IllegalArgumentException("Dia da semana não pode ser nulo.");
        }

        DayOfWeek day = DIA_SEMANA_MAP.get(dia.trim().toUpperCase());

        if (day == null) {
            throw new IllegalArgumentException("Dia da semana inválido: " + dia);
        }

        return day;
    }
}
