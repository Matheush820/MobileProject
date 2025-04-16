package com.salafacil.SalaFacilSpace.services;

import com.salafacil.SalaFacilSpace.dto.ReservaDTO;
import com.salafacil.SalaFacilSpace.entity.*;
import com.salafacil.SalaFacilSpace.exception.ResourceNotFoundException;
import com.salafacil.SalaFacilSpace.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        // Validar se já existe reserva para o mesmo laboratório, data e horário
        if (reservaRepository.existsByDataAndLaboratorioIdAndHorarioId(
                reservaDTO.getData(),
                reservaDTO.getLaboratorioId(),
                reservaDTO.getHorarioId())) {
            throw new IllegalArgumentException("Já existe reserva para este laboratório no horário selecionado");
        }

        // Buscar todas as entidades relacionadas
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

        // Criar a reserva
        Reserva reserva = Reserva.builder()
                .data(reservaDTO.getData())
                .professor(professor)
                .curso(curso)
                .laboratorio(laboratorio)
                .horario(horario)
                .categoria(categoria)
                .build();

        // Salvar no banco
        Reserva reservaSalva = reservaRepository.save(reserva);

        // Converter para DTO e retornar
        return convertToDTO(reservaSalva);
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
