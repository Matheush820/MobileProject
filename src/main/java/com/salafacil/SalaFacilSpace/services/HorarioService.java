package com.salafacil.SalaFacilSpace.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import com.salafacil.SalaFacilSpace.entity.Curso;
import com.salafacil.SalaFacilSpace.entity.Horario;
import com.salafacil.SalaFacilSpace.entity.Reserva;
import com.salafacil.SalaFacilSpace.repository.CursoRepository;
import com.salafacil.SalaFacilSpace.repository.HorarioRepository;
import com.salafacil.SalaFacilSpace.repository.ReservaRepository;

@Service
public class HorarioService {
	@Autowired
    private CursoRepository cursoRepository;
    @Autowired
    private HorarioRepository horarioRepository;
    @Autowired
    private ReservaRepository reservaRepository;


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
            horarioExistente.setHoraInicio(novoHorario.getHoraInicio());
            horarioExistente.setHoraFim(novoHorario.getHoraFim());
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
    
    public List<Horario> buscarHorariosDisponiveis(LocalDate data, String turno, Long cursoId, Long laboratorioId) {

        // Busca todos os horários do dia e turno
        List<Horario> horariosDoDiaETurno = horarioRepository.findByTurno(turno);

        // Busca reservas feitas nesse laboratório, data e curso
        List<Reserva> reservas = reservaRepository.findByDataAndLaboratorioIdAndCursoId(data, laboratorioId, cursoId);

        // Horários já reservados
        Set<Long> idsHorariosReservados = reservas.stream()
                .map(reserva -> reserva.getHorario().getId())
                .collect(Collectors.toSet());

        // Retorna apenas os horários disponíveis
        return horariosDoDiaETurno.stream()
                .filter(horario -> !idsHorariosReservados.contains(horario.getId()))
                .collect(Collectors.toList());
    }


    public List<Horario> listarHorariosPorCurso(Long cursoId) {
        Optional<Curso> cursoOpt = cursoRepository.findById(cursoId);
        return cursoOpt.map(curso -> List.copyOf(curso.getHorarios())).orElse(List.of());
    }

}
