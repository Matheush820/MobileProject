package com.salafacil.SalaFacilSpace.services;

import com.salafacil.SalaFacilSpace.dto.HorarioRequest;
import com.salafacil.SalaFacilSpace.dto.HorarioResponse;
import com.salafacil.SalaFacilSpace.entity.Horario;
import com.salafacil.SalaFacilSpace.exception.BusinessException;
import com.salafacil.SalaFacilSpace.exception.ResourceNotFoundException;
import com.salafacil.SalaFacilSpace.mapper.HorarioMapper;
import com.salafacil.SalaFacilSpace.repository.HorarioRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class HorarioService {

    private final HorarioRepository horarioRepository;

    public HorarioResponse createHorario(HorarioRequest request) {
        validateHorario(request);
        Horario saved = horarioRepository.save(HorarioMapper.toEntity(request));
        log.info("Horario criado: {}", saved.getId());
        return HorarioMapper.toResponse(saved);
    }

    public List<HorarioResponse> getAllHorarios() {
        return horarioRepository.findAll()
                .stream()
                .map(HorarioMapper::toResponse)
                .toList();
    }

    public HorarioResponse getHorarioById(Long id) {
        Horario horario = horarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Horario não encontrado"));
        return HorarioMapper.toResponse(horario);
    }

    public HorarioResponse updateHorario(Long id, HorarioRequest request) {
        validateHorario(request);
        Horario horario = horarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Horario não encontrado"));

        horario.setDiaSemana(request.diaSemana().name());
        horario.setTurno(request.turno().toUpperCase());
        horario.setHoraInicio(request.horaInicio());
        horario.setHoraFim(request.horaFim());

        horarioRepository.save(horario);
        return HorarioMapper.toResponse(horario);
    }

    public void deleteHorario(Long id) {
        if (!horarioRepository.existsById(id)) {
            throw new ResourceNotFoundException("Horario não encontrado");
        }
        horarioRepository.deleteById(id);
    }

    public List<HorarioResponse> buscarPorDataEPorTurno(LocalDate data, String turno) {
        DayOfWeek diaSemana = data.getDayOfWeek();
        return horarioRepository.findByDiaSemanaAndTurno(diaSemana, turno.toUpperCase())
                .stream()
                .map(HorarioMapper::toResponse)
                .toList();
    }

    private void validateHorario(HorarioRequest request) {
        if (request.horaInicio().isAfter(request.horaFim())) {
            throw new BusinessException("Hora de início não pode ser depois da hora de fim.");
        }
    }
}