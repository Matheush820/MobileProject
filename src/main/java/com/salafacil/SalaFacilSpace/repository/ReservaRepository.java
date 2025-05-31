package com.salafacil.SalaFacilSpace.repository;

import com.salafacil.SalaFacilSpace.entity.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface ReservaRepository extends JpaRepository<Reserva, Long> {

    boolean existsByDataAndLaboratorioIdAndHorarioId(LocalDate data, Long laboratorioId, Long horarioId);

    @Query("""
        SELECT r FROM Reserva r
        WHERE FUNCTION('TIMESTAMP', r.data, r.horario.horaFim) < :dataHora
    """)
    List<Reserva> findByDataHoraFimBefore(@Param("dataHora") LocalDateTime dataHora);

    List<Reserva> findByDataAndLaboratorioId(LocalDate data, Long laboratorioId);

    List<Reserva> findByDataAndLaboratorioIdAndCursoId(LocalDate data, Long laboratorioId, Long cursoId);

    List<Reserva> findByDataAndLaboratorioIdAndHorarioId(LocalDate data, Long laboratorioId, Long horarioId);

    List<Reserva> findByDataAndCursoId(LocalDate data, Long cursoId);

    //Novo método para buscar reservas por professorId
    List<Reserva> findByProfessorId(Long professorId);
}
