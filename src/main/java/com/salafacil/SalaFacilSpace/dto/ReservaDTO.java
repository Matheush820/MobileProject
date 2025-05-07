package com.salafacil.SalaFacilSpace.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReservaDTO {

    @NotNull(message = "Data é obrigatória")
    @PastOrPresent(message = "A data da reserva não pode ser no futuro")
    private LocalDate data;

    @NotNull(message = "Professor é obrigatório")
    private Long professorId;

    @NotNull(message = "Curso é obrigatório")
    private Long cursoId;

    @NotNull(message = "Laboratório é obrigatório")
    private Long laboratorioId;

    @NotNull(message = "Horário é obrigatório")
    private Long horarioId;

    @NotNull(message = "Categoria é obrigatória")
    private Long categoriaId;
}
