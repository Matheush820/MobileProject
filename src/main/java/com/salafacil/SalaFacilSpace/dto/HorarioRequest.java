package com.salafacil.SalaFacilSpace.dto;

import java.time.DayOfWeek;
import java.time.LocalTime;

public record HorarioRequest(DayOfWeek diaSemana, String turno, LocalTime horaInicio, LocalTime horaFim ) {

}