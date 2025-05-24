package com.salafacil.SalaFacilSpace.repository;

import com.salafacil.SalaFacilSpace.entity.Horario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HorarioRepository extends JpaRepository<Horario, Long> {
    
    // Busca por dia da semana e turno (exemplo: "SEGUNDA", "MANHA")
    List<Horario> findByTurno(String turno);
    }
