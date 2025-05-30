package com.salafacil.SalaFacilSpace.dto;

import java.time.format.DateTimeFormatter;

import com.salafacil.SalaFacilSpace.entity.Reserva;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReservaResponseDTO {
    private Long id;
    private String nomeProfessor;
    private String data;
    private String horario;
    private String nomeCurso;
    private String nomeLaboratorio;

    public ReservaResponseDTO(Reserva reserva) {
        this.id = reserva.getId();
        this.nomeProfessor = reserva.getProfessor().getNome();
        this.data = reserva.getData().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        this.horario = reserva.getHorario().toString();
        this.nomeCurso = reserva.getCurso().getNome();
        this.nomeLaboratorio = reserva.getLaboratorio().getNome();
    }
}
