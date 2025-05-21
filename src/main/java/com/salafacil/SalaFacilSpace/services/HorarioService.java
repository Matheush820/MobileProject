package com.salafacil.SalaFacilSpace.services;

import com.salafacil.SalaFacilSpace.entity.Horario;
import com.salafacil.SalaFacilSpace.repository.HorarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class HorarioService {

    @Autowired
    private HorarioRepository horarioRepository;

    public Horario createHorario(Horario horario) {
        return horarioRepository.save(horario);
    }

    public List<Horario> getAllHorarios() {
        return horarioRepository.findAll();
    }

    public Optional<Horario> getHorarioById(Long id) {
        return horarioRepository.findById(id);
    }

    public Optional<Horario> updateHorario(Long id, Horario novoHorario) {
        return horarioRepository.findById(id).map(horarioExistente -> {
            horarioExistente.setDiaSemana(novoHorario.getDiaSemana());
            horarioExistente.setHoraInicio(novoHorario.getHoraInicio());
            horarioExistente.setHoraFim(novoHorario.getHoraFim());
            horarioExistente.setTurno(novoHorario.getTurno());
            return horarioRepository.save(horarioExistente);
        });
    }

    public boolean deleteHorario(Long id) {
        if (horarioRepository.existsById(id)) {
            horarioRepository.deleteById(id);
            return true;
        }
        return false;
    }

    // 🔥 Buscar por data e turno
    public List<Horario> buscarHorariosPorDataEPorTurno(LocalDate data, String turno) {
        DayOfWeek diaSemana = data.getDayOfWeek();
        return horarioRepository.findByDiaSemanaAndTurno(diaSemana, turno.toUpperCase());
    }
}
