package com.salafacil.SalaFacilSpace.config;

import com.salafacil.SalaFacilSpace.entity.*;
import com.salafacil.SalaFacilSpace.repository.*;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalTime;
import java.util.List;

@Component
@RequiredArgsConstructor
public class DataSeeder {

    private final CategoriaRepository categoriaRepository;
    private final CursoRepository cursoRepository;
    private final LaboratorioRepository laboratorioRepository;
    private final HorarioRepository horarioRepository;

    @PostConstruct
    public void seedDatabase() {
        if (categoriaRepository.count() == 0) {
            System.out.println("DataSeeder foi instanciado!");

            // CATEGORIAS
            Categoria catTI = categoriaRepository.save(new Categoria(null, "Tecnologia da Informação", "Cursos ligados à área de computação e sistemas."));
            Categoria catSaude = categoriaRepository.save(new Categoria(null, "Saúde", "Cursos da área da saúde e bem-estar."));
            Categoria catEng = categoriaRepository.save(new Categoria(null, "Engenharia", "Cursos voltados para engenharia e construção."));
            Categoria catHumanas = categoriaRepository.save(new Categoria(null, "Ciências Humanas", "Cursos relacionados à área de humanas."));
            Categoria catExatas = categoriaRepository.save(new Categoria(null, "Ciências Exatas", "Cursos de matemática, física, química e correlatos."));
            Categoria catBiologicas = categoriaRepository.save(new Categoria(null, "Ciências Biológicas", "Cursos de biologia, biomedicina, genética e afins."));
            System.out.println(">>> DataSeeder.seedDatabase() iniciado!");

            // CURSOS
            cursoRepository.saveAll(List.of(
                // TI
                new Curso(null, "Análise e Desenvolvimento de Sistemas", catTI),
                new Curso(null, "Engenharia de Software", catTI),
                new Curso(null, "Ciência da Computação", catTI),

                // Saúde
                new Curso(null, "Enfermagem", catSaude),
                new Curso(null, "Biomedicina", catSaude),
                new Curso(null, "Fisioterapia", catSaude),

                // Engenharia
                new Curso(null, "Engenharia Civil", catEng),
                new Curso(null, "Engenharia Mecânica", catEng),
                new Curso(null, "Engenharia Elétrica", catEng),

                // Humanas
                new Curso(null, "Psicologia", catHumanas),
                new Curso(null, "História", catHumanas),
                new Curso(null, "Pedagogia", catHumanas),

                // Exatas
                new Curso(null, "Matemática", catExatas),
                new Curso(null, "Física", catExatas),
                new Curso(null, "Química", catExatas),

                // Biológicas
                new Curso(null, "Biologia", catBiologicas),
                new Curso(null, "Genética", catBiologicas),
                new Curso(null, "Biomedicina Avançada", catBiologicas)
            ));

            // LABORATÓRIOS com associação a Categoria
            laboratorioRepository.saveAll(List.of(
                // TI (Categoria: Tecnologia da Informação)
                new Laboratorio(null, "Laboratório de Tecnologia da Informação 01", "Bloco A", "A", "1", "101", catTI),
                new Laboratorio(null, "Laboratório de Tecnologia da Informação 02", "Bloco A", "A", "2", "102", catTI),

                // Saúde (Categoria: Saúde)
                new Laboratorio(null, "Laboratório de Saúde 01", "Bloco B", "B", "1", "201", catSaude),
                new Laboratorio(null, "Laboratório de Saúde 02", "Bloco B", "B", "2", "202", catSaude),
                new Laboratorio(null, "Laboratório de Anatomia", "Bloco B", "B", "3", "203", catSaude),

                // Engenharia (Categoria: Engenharia)
                new Laboratorio(null, "Laboratório de Engenharia Civil", "Bloco C", "C", "1", "301", catEng),
                new Laboratorio(null, "Laboratório de Engenharia Elétrica", "Bloco C", "C", "2", "302", catEng),
                new Laboratorio(null, "Laboratório de Engenharia Mecânica", "Bloco C", "C", "3", "303", catEng),

                // Multidisciplinar (associado a TI, ajuste se preferir outra)
                new Laboratorio(null, "Laboratório Multidisciplinar", "Bloco D", "D", "1", "401", catTI)
            ));

            // HORÁRIOS
            horarioRepository.saveAll(List.of(
                // Diurnos
                new Horario(null, "Segunda-feira", LocalTime.of(7, 30), LocalTime.of(11, 30)),
                new Horario(null, "Terça-feira", LocalTime.of(7, 30), LocalTime.of(11, 30)),
                new Horario(null, "Quarta-feira", LocalTime.of(7, 30), LocalTime.of(11, 30)),
                new Horario(null, "Quinta-feira", LocalTime.of(7, 30), LocalTime.of(11, 30)),
                new Horario(null, "Sexta-feira", LocalTime.of(7, 30), LocalTime.of(11, 30)),

                // Noturnos
                new Horario(null, "Segunda-feira", LocalTime.of(18, 30), LocalTime.of(22, 0)),
                new Horario(null, "Terça-feira", LocalTime.of(18, 30), LocalTime.of(22, 0)),
                new Horario(null, "Quarta-feira", LocalTime.of(19, 30), LocalTime.of(22, 0)),
                new Horario(null, "Quinta-feira", LocalTime.of(19, 30), LocalTime.of(22, 0)),
                new Horario(null, "Sexta-feira", LocalTime.of(18, 30), LocalTime.of(22, 0))
            ));

            System.out.println("✅ Dados iniciais inseridos com sucesso.");
        }
    }
}
