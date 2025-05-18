package com.salafacil.SalaFacilSpace.services;

import com.salafacil.SalaFacilSpace.dto.ReservaDTO;
import com.salafacil.SalaFacilSpace.entity.*;
import com.salafacil.SalaFacilSpace.exception.ResourceNotFoundException;
import com.salafacil.SalaFacilSpace.repository.*;
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

    @Transactional
    public ReservaDTO criarReserva(ReservaDTO reservaDTO) {
        if (reservaRepository.existsByDataAndLaboratorioIdAndHorarioId(
                reservaDTO.getData(),
                reservaDTO.getLaboratorioId(),
                reservaDTO.getHorarioId())) {
            throw new IllegalArgumentException("Já existe reserva para este laboratório no horário selecionado");
        }

        Professor professor = professorRepository.findById(reservaDTO.getProfessorId())
                .orElseThrow(() -> new ResourceNotFoundException("Professor não encontrado"));
        Curso curso = cursoRepository.findById(reservaDTO.getCursoId())
                .orElseThrow(() -> new ResourceNotFoundException("Curso não encontrado"));
        Laboratorio laboratorio = laboratorioRepository.findById(reservaDTO.getLaboratorioId())
                .orElseThrow(() -> new ResourceNotFoundException("Laboratório não encontrado"));
        Horario horario = horarioRepository.findById(reservaDTO.getHorarioId())
                .orElseThrow(() -> new ResourceNotFoundException("Horário não encontrado"));
        Categoria categoria = categoriaRepository.findById(reservaDTO.getCategoriaId())
                .orElseThrow(() -> new ResourceNotFoundException("Categoria não encontrada"));

        Reserva reserva = Reserva.builder()
                .data(reservaDTO.getData())
                .professor(professor)
                .curso(curso)
                .laboratorio(laboratorio)
                .horario(horario)
                .categoria(categoria)
                .build();

        Reserva reservaSalva = reservaRepository.save(reserva);
        return convertToDTO(reservaSalva);
    }

    @Transactional
    public ReservaDTO obterReserva(Long id) {
        Reserva reserva = reservaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Reserva não encontrada"));
        return convertToDTO(reserva);
    }

    @Transactional
    public List<ReservaDTO> listarTodasReservas() {
        List<Reserva> reservas = reservaRepository.findAll();
        return reservas.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    public ReservaDTO atualizarReserva(Long id, ReservaDTO reservaDTO) {
        Reserva reservaExistente = reservaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Reserva não encontrada"));

        if (reservaRepository.existsByDataAndLaboratorioIdAndHorarioId(
                reservaDTO.getData(),
                reservaDTO.getLaboratorioId(),
                reservaDTO.getHorarioId())) {
            throw new IllegalArgumentException("Já existe reserva para este laboratório no horário selecionado");
        }


        Professor professor = professorRepository.findById(reservaDTO.getProfessorId())
                .orElseThrow(() -> new ResourceNotFoundException("Professor não encontrado"));
        Curso curso = cursoRepository.findById(reservaDTO.getCursoId())
                .orElseThrow(() -> new ResourceNotFoundException("Curso não encontrado"));
        Laboratorio laboratorio = laboratorioRepository.findById(reservaDTO.getLaboratorioId())
                .orElseThrow(() -> new ResourceNotFoundException("Laboratório não encontrado"));
        Horario horario = horarioRepository.findById(reservaDTO.getHorarioId())
                .orElseThrow(() -> new ResourceNotFoundException("Horário não encontrado"));
        Categoria categoria = categoriaRepository.findById(reservaDTO.getCategoriaId())
                .orElseThrow(() -> new ResourceNotFoundException("Categoria não encontrada"));

        reservaExistente.setData(reservaDTO.getData());
        reservaExistente.setProfessor(professor);
        reservaExistente.setCurso(curso);
        reservaExistente.setLaboratorio(laboratorio);
        reservaExistente.setHorario(horario);
        reservaExistente.setCategoria(categoria);

        Reserva reservaAtualizada = reservaRepository.save(reservaExistente);
        return convertToDTO(reservaAtualizada);
    }

    @Transactional
    public void deletarReserva(Long id) {
        Reserva reserva = reservaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Reserva não encontrada"));
        reservaRepository.delete(reserva);
    }

    private ReservaDTO convertToDTO(Reserva reserva) {
        return ReservaDTO.builder()
                .data(reserva.getData())
                .professorId(reserva.getProfessor().getId())
                .cursoId(reserva.getCurso().getId())
                .laboratorioId(reserva.getLaboratorio().getId())
                .horarioId(reserva.getHorario().getId())
                .categoriaId(reserva.getCategoria().getId())
                .build();
    }
}
