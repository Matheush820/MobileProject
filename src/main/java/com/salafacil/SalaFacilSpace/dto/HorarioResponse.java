package com.salafacil.SalaFacilSpace.dto;

import java.time.DayOfWeek;
import java.time.LocalTime;


public record HorarioResponse(Long id, DayOfWeek diaSemana, String turno, LocalTime horaInicio, LocalTime horaFim) {}
