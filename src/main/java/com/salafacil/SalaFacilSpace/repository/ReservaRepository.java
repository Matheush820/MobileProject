package com.salafacil.SalaFacilSpace.repository;

import com.salafacil.SalaFacilSpace.entity.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface ReservaRepository extends JpaRepository<Reserva, Long> {
    boolean existsByDataAndLaboratorioIdAndHorarioId(LocalDate data, Long laboratorioId, Long horarioId);
    
    List<Reserva> findByDataHoraFimBefore(LocalDateTime dataHora);

}
