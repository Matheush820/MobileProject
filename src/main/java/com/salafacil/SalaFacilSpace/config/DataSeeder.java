package com.salafacil.SalaFacilSpace.config;

import com.salafacil.SalaFacilSpace.entity.*;
import com.salafacil.SalaFacilSpace.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
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

        // 1. Categorias
        Categoria tecnologia = categoriaRepository.save(new Categoria(null, "Tecnologia", "Cursos de tecnologia."));
        Categoria saude = categoriaRepository.save(new Categoria(null, "Saúde", "Cursos da área da saúde."));
        Categoria engenharia = categoriaRepository.save(new Categoria(null, "Engenharia", "Engenharia e afins."));
        Categoria humanas = categoriaRepository.save(new Categoria(null, "Ciências Humanas", "Cursos da área de humanas."));
        Categoria exatas = categoriaRepository.save(new Categoria(null, "Ciências Exatas", "Cursos da área de exatas."));
        Categoria biociencias = categoriaRepository.save(new Categoria(null, "Biociências", "Cursos da área biológica."));

        // 2. Cursos (sem horários ainda)
        Curso sistemas = cursoRepository.save(Curso.builder()
                .nome("Sistemas de Informação")
                .categoria(tecnologia)
                .build());

        Curso enfermagem = cursoRepository.save(Curso.builder()
                .nome("Enfermagem")
                .categoria(saude)
                .build());

        Curso engenhariaCivil = cursoRepository.save(Curso.builder()
                .nome("Engenharia Civil")
                .categoria(engenharia)
                .build());

        // 3. Horários (já com cursos associados)
        Horario horario1 = horarioRepository.save(new Horario(null, LocalTime.of(8, 0), LocalTime.of(10, 0), "MANHÃ", sistemas));
        Horario horario2 = horarioRepository.save(new Horario(null, LocalTime.of(10, 15), LocalTime.of(12, 15), "MANHÃ", sistemas));
        Horario horario3 = horarioRepository.save(new Horario(null, LocalTime.of(14, 0), LocalTime.of(16, 0), "TARDE", enfermagem));
        Horario horario4 = horarioRepository.save(new Horario(null, LocalTime.of(16, 15), LocalTime.of(18, 15), "TARDE", enfermagem));
        Horario horario5 = horarioRepository.save(new Horario(null, LocalTime.of(19, 0), LocalTime.of(21, 0), "NOITE", sistemas));

        // Duplicar horários para engenharia civil (já que não existe mais ManyToMany)
        Horario horario6 = horarioRepository.save(new Horario(null, LocalTime.of(8, 0), LocalTime.of(10, 0), "MANHÃ", engenhariaCivil));
        Horario horario7 = horarioRepository.save(new Horario(null, LocalTime.of(14, 0), LocalTime.of(16, 0), "TARDE", engenhariaCivil));
        Horario horario8 = horarioRepository.save(new Horario(null, LocalTime.of(19, 0), LocalTime.of(21, 0), "NOITE", engenhariaCivil));

        // 4. Laboratórios
        laboratorioRepository.saveAll(List.of(
                new Laboratorio(null, "Laboratório de Tecnologia da Informação 01", "Bloco A", "A", "1", "101", tecnologia),
                new Laboratorio(null, "Laboratório de Tecnologia da Informação 02", "Bloco A", "A", "2", "102", tecnologia),
                new Laboratorio(null, "Laboratório de Saúde 01", "Bloco B", "B", "1", "201", saude),
                new Laboratorio(null, "Laboratório de Saúde 02", "Bloco B", "B", "2", "202", saude),
                new Laboratorio(null, "Laboratório de Anatomia", "Bloco B", "B", "3", "203", saude),
                new Laboratorio(null, "Laboratório de Engenharia Civil", "Bloco C", "C", "1", "301", engenharia),
                new Laboratorio(null, "Laboratório de Engenharia Elétrica", "Bloco C", "C", "2", "302", engenharia),
                new Laboratorio(null, "Laboratório de Engenharia Mecânica", "Bloco C", "C", "3", "303", engenharia),
                new Laboratorio(null, "Laboratório Multidisciplinar", "Bloco D", "D", "1", "401", tecnologia),
                new Laboratorio(null, "Laboratório de Psicologia", "Bloco B", "B", "1", "333", humanas),
                new Laboratorio(null, "Laboratório de Sociologia", "Bloco A", "A", "2", "171", humanas),
                new Laboratorio(null, "Laboratório de Matemática Aplicada", "Bloco A", "A", "1", "222", exatas),
                new Laboratorio(null, "Laboratório de Física Experimental", "Bloco A", "A", "2", "255", exatas),
                new Laboratorio(null, "Laboratório de Química", "Bloco B", "B", "3", "412", exatas),
                new Laboratorio(null, "Laboratório de Biologia Molecular", "Bloco B", "B", "1", "10", biociencias),
                new Laboratorio(null, "Laboratório de Genética", "Bloco A", "B", "2", "123", biociencias),
                new Laboratorio(null, "Laboratório de Biomedicina", "Bloco B", "A", "3", "321", biociencias)
        ));

        System.out.println("Dados inseridos com sucesso.");
    }
}
