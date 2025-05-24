package com.salafacil.SalaFacilSpace.services;

import com.salafacil.SalaFacilSpace.dto.ReservaDTO;
import com.salafacil.SalaFacilSpace.entity.*;
import com.salafacil.SalaFacilSpace.exception.ResourceNotFoundException;
import com.salafacil.SalaFacilSpace.mapper.ReservaMapper;
import com.salafacil.SalaFacilSpace.repository.*;
import com.salafacil.SalaFacilSpace.validator.ReservaValidator;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReservaService {

    private final ReservaRepository reservaRepository;
    private final ProfessorRepository professorRepository;
    private final CursoRepository cursoRepository;
    private final LaboratorioRepository laboratorioRepository;
    private final HorarioRepository horarioRepository;
    private final CategoriaRepository categoriaRepository;
    private final ReservaValidator reservaValidator;

    @Transactional
    public ReservaDTO criarReserva(ReservaDTO reservaDTO) {
        reservaValidator.validarConflito(reservaDTO);

        Reserva reserva = Reserva.builder()
                .data(reservaDTO.getData())
                .professor(buscarProfessor(reservaDTO.getProfessorId()))
                .curso(buscarCurso(reservaDTO.getCursoId()))
                .laboratorio(buscarLaboratorio(reservaDTO.getLaboratorioId()))
                .horario(buscarHorario(reservaDTO.getHorarioId()))
                .categoria(buscarCategoria(reservaDTO.getCategoriaId()))
                .build();

        return ReservaMapper.toDTO(reservaRepository.save(reserva));
    }

    @Transactional
    public ReservaDTO obterReserva(Long id) {
        return ReservaMapper.toDTO(buscarReserva(id));
    }

    @Transactional
    public List<ReservaDTO> listarTodasReservas() {
        return reservaRepository.findAll().stream()
                .map(ReservaMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    public ReservaDTO atualizarReserva(Long id, ReservaDTO reservaDTO) {
        Reserva reserva = buscarReserva(id);
        reservaValidator.validarConflito(reservaDTO);

        reserva.setData(reservaDTO.getData());
        reserva.setProfessor(buscarProfessor(reservaDTO.getProfessorId()));
        reserva.setCurso(buscarCurso(reservaDTO.getCursoId()));
        reserva.setLaboratorio(buscarLaboratorio(reservaDTO.getLaboratorioId()));
        reserva.setHorario(buscarHorario(reservaDTO.getHorarioId()));
        reserva.setCategoria(buscarCategoria(reservaDTO.getCategoriaId()));

        return ReservaMapper.toDTO(reservaRepository.save(reserva));
    }

    @Transactional
    public void deletarReserva(Long id) {
        reservaRepository.delete(buscarReserva(id));
    }

    // 🔥 Helpers privados para limpar o service
    private Reserva buscarReserva(Long id) {
        return reservaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Reserva não encontrada com ID: " + id));
    }

    private Professor buscarProfessor(Long id) {
        return professorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Professor não encontrado com ID: " + id));
    }

    private Curso buscarCurso(Long id) {
        return cursoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Curso não encontrado com ID: " + id));
    }

    private Laboratorio buscarLaboratorio(Long id) {
        return laboratorioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Laboratório não encontrado com ID: " + id));
    }

    private Horario buscarHorario(Long id) {
        return horarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Horário não encontrado com ID: " + id));
    }

    private Categoria buscarCategoria(Long id) {
        return categoriaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Categoria não encontrada com ID: " + id));
    }
}
