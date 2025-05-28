package com.salafacil.SalaFacilSpace.repository;

import com.salafacil.SalaFacilSpace.entity.Horario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.DayOfWeek;
import java.util.List;

public interface HorarioRepository extends JpaRepository<Horario, Long> {
    
    // Buscar por dia da semana
    List<Horario> findByDiaSemana(DayOfWeek diaSemana);

    // 🔥 Buscar por dia da semana e turno
    List<Horario> findByDiaSemanaAndTurno(DayOfWeek diaSemana, String turno);
    }
