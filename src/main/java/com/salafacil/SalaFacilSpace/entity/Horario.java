package com.salafacil.SalaFacilSpace.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "horario")
public class Horario {
    
	  @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;

	    @NotBlank(message = "Dia da semana é obrigatório")
	    private String diaSemana; // Exemplo: SEGUNDA, TERÇA, ...

	    @NotNull(message = "Hora de início é obrigatória")
	    private LocalTime horaInicio;

	    @NotNull(message = "Hora de fim é obrigatória")
	    private LocalTime horaFim;

	    @NotBlank(message = "Turno é obrigatório")
	    private String turno; // MANHA, TARDE, NOITE

}