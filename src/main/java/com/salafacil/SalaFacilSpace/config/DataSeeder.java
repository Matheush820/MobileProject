package com.salafacil.SalaFacilSpace.config;

import com.salafacil.SalaFacilSpace.entity.*;
import com.salafacil.SalaFacilSpace.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Component
@RequiredArgsConstructor
public class DataSeeder implements CommandLineRunner {

    private final CategoriaRepository categoriaRepository;
    private final CursoRepository cursoRepository;
    private final HorarioRepository horarioRepository;
    private final LaboratorioRepository laboratorioRepository;
    private final ProfessorRepository professorRepository;
    private final ReservaRepository reservaRepository;

    @Override
    public void run(String... args) {
        if (categoriaRepository.count() > 0 || cursoRepository.count() > 0 || horarioRepository.count() > 0 ||
            laboratorioRepository.count() > 0 || professorRepository.count() > 0 || reservaRepository.count() > 0) {
            return; // Já existem dados, então não popula novamente
        }

        // 1. Categorias
        Categoria informatica = categoriaRepository.save(new Categoria(null, "Informática", "Laboratórios de informática em geral."));
        Categoria saude = categoriaRepository.save(new Categoria(null, "Saúde", "Laboratórios de Saúde"));
        Categoria engenharia = categoriaRepository.save(new Categoria(null, "Engenharia", "Engenharia civil, elétrica, mecânica e afins."));
        Categoria biologia = categoriaRepository.save(new Categoria(null, "Ciências Biológicas", "Categoria sobre Biologia"));
        Categoria humanas = categoriaRepository.save(new Categoria(null, "Humanas", "Categorias de Humanas"));
        Categoria cienciasExatas = categoriaRepository.save(new Categoria(null, "Ciências Exatas", "Categoria para Matemática, Fisica e Quimica."));
        Categoria cienciasHumanas = categoriaRepository.save(new Categoria(null, "Ciências Humanas", "Categoria para Historia, geografia e Filosofia."));

        // 2. Cursos
        Curso cursoSelecionado = cursoRepository.saveAll(List.of(
            Curso.builder().nome("Farmácia").periodo("Integral").build(),
            Curso.builder().nome("Ciência da Computação").periodo("Noturno").build(),
            Curso.builder().nome("Medicina").periodo("Noturno").build(),
            Curso.builder().nome("Engenharia Elétrica").periodo("Integral").build(),
            Curso.builder().nome("Engenharia Mecatrônica").periodo("Integral").build(),
            Curso.builder().nome("Fisioterapia").periodo("Noturno").build(),
            Curso.builder().nome("Engenharia de Produção").periodo("Noturno").build(),
            Curso.builder().nome("Análise e Desenvolvimento de Sistemas").periodo("Noturno").build(),
            Curso.builder().nome("Licenciatura em Matemática").periodo("Noturno").build(),
            Curso.builder().nome("Licenciatura em História").periodo("Noturno").build()
        )).get(0);

        // 3. Horários - Segunda a Sexta com nome completo
        Horario horarioSelecionado = horarioRepository.saveAll(List.of(
            // SEGUNDA-FEIRA
            Horario.builder().diaSemana("SEGUNDA-FEIRA").horaInicio(LocalTime.of(7, 0)).horaFim(LocalTime.of(8, 40)).turno("MANHÃ").build(),
            Horario.builder().diaSemana("SEGUNDA-FEIRA").horaInicio(LocalTime.of(8, 50)).horaFim(LocalTime.of(10, 30)).turno("MANHÃ").build(),
            Horario.builder().diaSemana("SEGUNDA-FEIRA").horaInicio(LocalTime.of(10, 40)).horaFim(LocalTime.of(12, 20)).turno("MANHÃ").build(),

            // TERÇA-FEIRA
            Horario.builder().diaSemana("TERÇA-FEIRA").horaInicio(LocalTime.of(13, 0)).horaFim(LocalTime.of(14, 40)).turno("TARDE").build(),
            Horario.builder().diaSemana("TERÇA-FEIRA").horaInicio(LocalTime.of(14, 50)).horaFim(LocalTime.of(16, 30)).turno("TARDE").build(),
            Horario.builder().diaSemana("TERÇA-FEIRA").horaInicio(LocalTime.of(16, 40)).horaFim(LocalTime.of(18, 20)).turno("TARDE").build(),

            // QUARTA-FEIRA
            Horario.builder().diaSemana("QUARTA-FEIRA").horaInicio(LocalTime.of(18, 30)).horaFim(LocalTime.of(20, 10)).turno("NOITE").build(),
            Horario.builder().diaSemana("QUARTA-FEIRA").horaInicio(LocalTime.of(20, 20)).horaFim(LocalTime.of(22, 0)).turno("NOITE").build(),

            // QUINTA-FEIRA
            Horario.builder().diaSemana("QUINTA-FEIRA").horaInicio(LocalTime.of(7, 0)).horaFim(LocalTime.of(8, 40)).turno("MANHÃ").build(),
            Horario.builder().diaSemana("QUINTA-FEIRA").horaInicio(LocalTime.of(8, 50)).horaFim(LocalTime.of(10, 30)).turno("MANHÃ").build(),

            // SEXTA-FEIRA
            Horario.builder().diaSemana("SEXTA-FEIRA").horaInicio(LocalTime.of(13, 0)).horaFim(LocalTime.of(14, 40)).turno("TARDE").build(),
            Horario.builder().diaSemana("SEXTA-FEIRA").horaInicio(LocalTime.of(14, 50)).horaFim(LocalTime.of(16, 30)).turno("TARDE").build()
        )).get(0);

        // 4. Laboratórios
        Laboratorio laboratorioSelecionado = laboratorioRepository.saveAll(List.of(
            new Laboratorio(null, "Laboratório de Informática I", "Bloco B - Sala 101", "B", "1", "101", informatica),
            new Laboratorio(null, "Laboratório de Informática II", "Bloco B - Sala 102", "B", "1", "102", informatica),
            new Laboratorio(null, "Laboratório de Saúde", "Bloco A - Sala 201", "C", "2", "201", saude),
            new Laboratorio(null, "Laboratório de Medicina Veterinaria", "Bloco A - Sala 202", "C", "2", "201", saude),
            new Laboratorio(null, "Laboratório de Engenharia", "Bloco A - Sala 202", "C", "2", "202", engenharia),
            new Laboratorio(null, "Laboratório de Engenharia Mecânica", "Bloco B - Sala 303", "D", "3", "303", engenharia),
            new Laboratorio(null, "Laboratório de Biologia I", "Bloco A - Sala 305", "D", "3", "305", biologia),
            new Laboratorio(null, "Laboratório de Farmácia", "Bloco B - Sala 345", "B", "3", "305", saude),
            new Laboratorio(null, "Laboratório de Biologia II", "Bloco A - Sala 310", "A", "3", "305", biologia),
            new Laboratorio(null, "Laboratório de Humanas I", "Bloco A - Sala 401", "A", "4", "401", humanas),
            new Laboratorio(null, "Laboratório de Humanas II", "Bloco A - Sala 402", "A", "4", "401", humanas)

        )).get(0);

       
    }
}
